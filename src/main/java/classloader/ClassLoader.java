package classloader;

import classloader.classfileparser.ClassFile;
import classloader.classfilereader.ClassFileReader;
import classloader.classfilereader.classpath.EntryType;
import com.sun.tools.javac.util.Pair;
import memory.MethodArea;
import memory.jclass.Field;
import memory.jclass.JClass;
import memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import memory.jclass.runtimeConstantPool.constant.wrapper.DoubleWrapper;
import memory.jclass.runtimeConstantPool.constant.wrapper.FloatWrapper;
import memory.jclass.runtimeConstantPool.constant.wrapper.IntWrapper;
import memory.jclass.runtimeConstantPool.constant.wrapper.LongWrapper;
import runtime.Vars;

import java.io.IOException;

/**
 * Description:
 * Load class file
 *
 * @author xxz
 * Created on 2020-02-10
 */
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
     * @param className       name of class
     * @param initiatingEntry null value represents load MainClass
     * @throws ClassNotFoundException cnf
     */
    public JClass loadClass(String className, EntryType initiatingEntry) throws ClassNotFoundException {
        JClass ret = null;
        if ((ret = methodArea.findClass(className)) == null) {
            //array and nonarray
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

    private void loadArrayClass() {

    }

    private JClass defineClass(byte[] data, EntryType definingEntry) throws ClassNotFoundException {
        //XXX: 这是啥玩意儿？
        //todo:create classfile need to handle java.lang.ClassFormatError
        ClassFile classFile = new ClassFile(data);
        JClass clazz = new JClass(classFile);
        clazz.setLoadEntryType(definingEntry);
        resolveSuperClass(clazz);
        resolveInterfaces(clazz);
        methodArea.addClass(clazz.getName(), clazz);
        return clazz;
    }

    private void resolveSuperClass(JClass clazz) throws ClassNotFoundException {
        if (!clazz.getName().equals("java/lang/Object")) {
            String superClassName = clazz.getSuperClassName();
            EntryType initiatingEntry = clazz.getLoadEntryType();
            clazz.setSuperClass(loadClass(superClassName, initiatingEntry));
        }
    }

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

    private void linkClass(JClass clazz) {
        verify(clazz);
        prepare(clazz);
    }

    /**
     * todo:homework
     */
    private void verify(JClass clazz) {
        //do nothing
    }

    private void prepare(JClass clazz) {
        calInstanceFieldSlotIDs(clazz);
        calStaticFieldSlotIDs(clazz);
        allocAndInitStaticVars(clazz);
    }

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

    private void allocAndInitStaticVars(JClass clazz) {
        clazz.setStaticVars(new Vars(clazz.getStaticSlotCount()));
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            if (f.isStatic() && f.isFinal()) {
                //load const value from runtimeConstantPool for base type or String
                Vars staticVars = clazz.getStaticVars();
                RuntimeConstantPool runtimeConstantPool = clazz.getRuntimeConstantPool();
                int constantPoolIndex = f.getConstValueIndex();
                int slotID = f.getSlotID();
                if (constantPoolIndex > 0) {
                    switch (f.getDescriptor()) {
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
                        case "Ljava/lang/String;":
                            //todo:
                            break;

                    }
                }
            }
        }
    }
}
