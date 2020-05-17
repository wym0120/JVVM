package classloader;

import classloader.classfileparser.ClassFile;
import classloader.classfilereader.ClassFileReader;
import classloader.classfilereader.classpath.EntryType;
import com.sun.tools.javac.util.Pair;
import memory.MethodArea;
import memory.jclass.AccessFlags;
import memory.jclass.Field;
import memory.jclass.InitState;
import memory.jclass.JClass;
import memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import memory.jclass.runtimeConstantPool.constant.wrapper.DoubleWrapper;
import memory.jclass.runtimeConstantPool.constant.wrapper.FloatWrapper;
import memory.jclass.runtimeConstantPool.constant.wrapper.IntWrapper;
import memory.jclass.runtimeConstantPool.constant.wrapper.LongWrapper;
import runtime.Vars;
import runtime.struct.NullObject;

import java.io.IOException;

public class ClassLoader {
    private static ClassLoader classLoader = new ClassLoader();
    private ClassFileReader classFileReader;
    private MethodArea methodArea;

    private ClassLoader() {
        classFileReader = ClassFileReader.getInstance();
        methodArea = MethodArea.getInstance();
    }

    public static ClassLoader getInstance() {
        return classLoader;
    }

    /**
     * load phase
     *
     * @param className       name of class
     * @param initiatingEntry null value represents load MainClass
     */
    public JClass loadClass(String className, EntryType initiatingEntry) throws ClassNotFoundException {
        JClass ret;
        if ((ret = methodArea.findClass(className)) == null) {
            if (className.charAt(0) == '[') {
                return loadArrayClass(className, initiatingEntry);
            }
            return loadNonArrayClass(className, initiatingEntry);
        }
        return ret;
    }

    private JClass loadNonArrayClass(String className, EntryType initiatingEntry) throws ClassNotFoundException {
        try {
            Pair<byte[], Integer> res = classFileReader.readClassFile(className, initiatingEntry);
            byte[] data = res.fst;
            EntryType definingEntry = new EntryType(res.snd);
            //define class
            JClass clazz = defineClass(data, definingEntry);
            //link class
            linkClass(clazz);
            return clazz;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }

    private JClass loadArrayClass(String className, EntryType initiatingEntry) {
        JClass ret = new JClass();
        ret.setAccessFlags((short) AccessFlags.ACC_PUBLIC);
        ret.setName(className);
        ret.setInitState(InitState.SUCCESS);
        methodArea.addClass(ret.getName(), ret);
        try {
            ret.setSuperClass(tryLoad("java/lang/Object", initiatingEntry));
            JClass[] interfaces = {tryLoad("java/lang/Cloneable", initiatingEntry)
                    , tryLoad("java/io/Serializable", initiatingEntry)};
            ret.setInterfaces(interfaces);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ret;

    }

    private JClass tryLoad(String className, EntryType initiatingEntry) throws ClassNotFoundException {
        JClass ret;
        if ((ret = methodArea.findClass(className)) == null) {
            return loadNonArrayClass(className, initiatingEntry);
        } else {
            return ret;
        }
    }

    private JClass defineClass(byte[] data, EntryType definingEntry) throws ClassNotFoundException {
        ClassFile classFile = new ClassFile(data);
        JClass clazz = new JClass(classFile);
        clazz.setLoadEntryType(definingEntry);
        resolveSuperClass(clazz);
        resolveInterfaces(clazz);
        methodArea.addClass(clazz.getName(), clazz);
        return clazz;
    }

    /**
     * load superclass before add to method area
     */
    private void resolveSuperClass(JClass clazz) throws ClassNotFoundException {
        if (!clazz.getName().equals("java/lang/Object")) {
            String superClassName = clazz.getSuperClassName();
            EntryType initiatingEntry = clazz.getLoadEntryType();
            clazz.setSuperClass(loadClass(superClassName, initiatingEntry));
        }
    }

    /**
     * load interfaces before add to method area
     */
    private void resolveInterfaces(JClass clazz) throws ClassNotFoundException {
        EntryType initiatingEntry = clazz.getLoadEntryType();
        String[] interfaceNames = clazz.getInterfaceNames();
        int interfaceCount = interfaceNames.length;
        JClass[] interfaces = new JClass[interfaceCount];
        clazz.setInterfaces(interfaces);
        for (int i = 0; i < interfaceCount; i++) {
            interfaces[i] = loadClass(interfaceNames[i], initiatingEntry);
        }
    }

    /**
     * link phase
     */
    private void linkClass(JClass clazz) {
        verify(clazz);
        prepare(clazz);
    }

    private void verify(JClass clazz) {
        //do nothing
    }

    private void prepare(JClass clazz) {
        calInstanceFieldSlotIDs(clazz);
        calStaticFieldSlotIDs(clazz);
        allocAndInitStaticVars(clazz);
        clazz.setInitState(InitState.PREPARED);
    }

    /**
     * count the number of field slots in instance
     * long and double takes two slots
     * the field is not static
     */
    private void calInstanceFieldSlotIDs(JClass clazz) {
        int slotID = 0;
        if (clazz.getSuperClass() != null) {
            slotID = clazz.getSuperClass().getInstanceSlotCount();
        }
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            if (!f.isStatic()) {
                f.setSlotID(slotID);
                slotID++;
                if (f.isLongOrDouble()) slotID++;
            }
        }
        clazz.setInstanceSlotCount(slotID);
    }

    /**
     * count the number of field slots in class
     * long and double takes two slots
     * the field is static
     */
    private void calStaticFieldSlotIDs(JClass clazz) {
        int slotID = 0;
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            if (f.isStatic()) {
                f.setSlotID(slotID);
                slotID++;
                if (f.isLongOrDouble()) slotID++;
            }
        }
        clazz.setStaticSlotCount(slotID);
    }

    /**
     * primitive type is set to 0
     * ref type is set to null
     */
    private void initDefaultValue(JClass clazz, Field field) {
        Vars staticVars = clazz.getStaticVars();
        int slotID = field.getSlotID();
        String descriptor = field.getDescriptor();
        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                staticVars.setInt(slotID, 0);
                break;
            case 'F':
                staticVars.setFloat(slotID, 0);
                break;
            case 'J':
                staticVars.setLong(slotID, 0);
                break;
            case 'D':
                staticVars.setDouble(slotID, 0);
                break;
            default:
                staticVars.setObjectRef(slotID, new NullObject());
                break;
        }
    }

    /**
     * load const value from runtimeConstantPool for primitive type
     * String is not support now
     */
    private void loadValueFromRTCP(JClass clazz, Field field) {
        Vars staticVars = clazz.getStaticVars();
        RuntimeConstantPool runtimeConstantPool = clazz.getRuntimeConstantPool();
        int constantPoolIndex = field.getConstValueIndex();
        int slotID = field.getSlotID();
        if (constantPoolIndex > 0) {
            switch (field.getDescriptor()) {
                case "Z":
                case "B":
                case "C":
                case "S":
                case "I":
                    int intVal = ((IntWrapper) runtimeConstantPool.getConstant(constantPoolIndex)).getValue();
                    staticVars.setInt(slotID, intVal);
                    break;
                case "J":
                    long longVal = ((LongWrapper) runtimeConstantPool.getConstant(constantPoolIndex)).getValue();
                    staticVars.setLong(slotID, longVal);
                    break;
                case "D":
                    double doubleVal = ((DoubleWrapper) runtimeConstantPool.getConstant(constantPoolIndex)).getValue();
                    staticVars.setDouble(slotID, doubleVal);
                    break;
                case "F":
                    float floatVal = ((FloatWrapper) runtimeConstantPool.getConstant(constantPoolIndex)).getValue();
                    staticVars.setFloat(slotID, floatVal);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * the value of static final field is in runtime constant pool
     * others will be set to default value
     */
    private void allocAndInitStaticVars(JClass clazz) {
        clazz.setStaticVars(new Vars(clazz.getStaticSlotCount()));
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            if (f.isStatic() && f.isFinal()) {
                loadValueFromRTCP(clazz, f);
            } else if (f.isStatic()) {
                initDefaultValue(clazz, f);
            }
        }
    }
}
