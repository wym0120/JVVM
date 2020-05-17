package memory.jclass;

import classloader.ClassLoader;
import classloader.classfileparser.ClassFile;
import classloader.classfileparser.FieldInfo;
import classloader.classfileparser.MethodInfo;
import classloader.classfileparser.constantpool.ConstantPool;
import classloader.classfilereader.classpath.EntryType;
import lombok.Getter;
import lombok.Setter;
import memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import runtime.JThread;
import runtime.StackFrame;
import runtime.Vars;
import runtime.struct.ArrayObject;
import runtime.struct.NonArrayObject;
import runtime.struct.array.*;

import java.util.HashMap;
import java.util.Optional;

@Getter
@Setter
public class JClass {
    private short accessFlags;
    private String name;
    private String superClassName;
    private String[] interfaceNames;
    private RuntimeConstantPool runtimeConstantPool;
    private Field[] fields;//
    private Method[] methods;//
    private EntryType loadEntryType;
    private JClass superClass;
    private JClass[] interfaces;
    private int instanceSlotCount;
    private int staticSlotCount;
    private Vars staticVars;
    private InitState initState;

    public JClass(ClassFile classFile) {
        this.accessFlags = classFile.getAccessFlags();
        this.name = classFile.getClassName();
        if (!this.name.equals("java/lang/Object")) {
            // index of super class of java/lang/Object is 0
            this.superClassName = classFile.getSuperClassName();
        } else {
            this.superClassName = "";
        }
        this.interfaceNames = classFile.getInterfaceNames();
        this.fields = parseFields(classFile.getFields());
        this.methods = parseMethods(classFile.getMethods());
        this.runtimeConstantPool = parseRuntimeConstantPool(classFile.getConstantPool());
    }

    //used for new array class!!!
    public JClass(){

    }

    public Optional<Method> resolveMethod(String name, String descriptor) {
        for (Method m : methods) {
            if (m.getDescriptor().equals(descriptor) && m.getName().equals(name)) {
                return Optional.of(m);
            }
        }
        return Optional.empty();
    }

    private Field[] parseFields(FieldInfo[] info) {
        int len = info.length;
        fields = new Field[len];
        for (int i = 0; i < len; i++) {
            fields[i] = new Field(info[i], this);
        }
        return fields;
    }

    private Method[] parseMethods(MethodInfo[] info) {
        int len = info.length;
        methods = new Method[len];
        for (int i = 0; i < len; i++) {
            methods[i] = new Method(info[i], this);
        }
        return methods;
    }

    private RuntimeConstantPool parseRuntimeConstantPool(ConstantPool cp) {
        return new RuntimeConstantPool(cp, this);
    }

    public NonArrayObject newObject() {
        return new NonArrayObject(this);
    }

    public ArrayObject newArrayObject(int len){
        if(this.name.charAt(0)!='[')throw new RuntimeException("This Class is not array: "+this.name);
        switch (this.name){
            case "[Z":
                return new BooleanArrayObject(len, this.name);
            case "[B":
                return new ByteArrayObject(len, this.name);
            case "[C":
                return new CharArrayObject(len, this.name);
            case "[S":
                return new ShortArrayObject(len, this.name);
            case "[I":
                return new IntArrayObject(len, this.name);
            case "[J":
                return new LongArrayObject(len, this.name);
            case "[F":
                return new FloatArrayObject(len, this.name);
            case "[D":
                return new DoubleArrayObject(len, this.name);
            default:
                return new RefArrayObject(len, this.name);
        }
    }

    /**
     * use for anewarray inst
     */
    public JClass getArrayClass(){
        //get descriptor

        String arrayClassName;
        if (this.name.charAt(0) == '[') {
            arrayClassName = this.name;
        } else if (getPrimitiveType() != null) {
            arrayClassName = getPrimitiveType();
        } else {
            arrayClassName = "L" + this.name + ";";
        }
        //generate array class name
        arrayClassName = "[" + arrayClassName;
        try {
            return ClassLoader.getInstance().loadClass(arrayClassName, this.loadEntryType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Cannot load arrayClass:" + arrayClassName);
    }

    public JClass getComponentClass() {
        if (this.name.charAt(0) != '[') throw new RuntimeException("Invalid Array:" + this.name);
        ClassLoader loader = ClassLoader.getInstance();
        String componentTypeDescriptor = this.name.substring(1);
        String classToLoad = null;
        if (componentTypeDescriptor.charAt(0) == '[') {
            classToLoad = componentTypeDescriptor;
        } else if (componentTypeDescriptor.charAt(0) == 'L') {
            //remove first and last char 'L' and ';'
            classToLoad = componentTypeDescriptor.substring(1, componentTypeDescriptor.length() - 1);
        } else if (getPrimitiveType() != null) {
            classToLoad = getPrimitiveType();
        }
        try {
            return loader.loadClass(classToLoad, this.loadEntryType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Cannot load arrayClass:" + classToLoad);
    }

    /**
     * @return null if this classname is not a primitive type
     */
    private String getPrimitiveType() {
        HashMap<String, String> primitiveType = new HashMap<>();
        primitiveType.put("void", "V");
        primitiveType.put("boolean", "Z");
        primitiveType.put("byte", "B");
        primitiveType.put("short", "S");
        primitiveType.put("char", "C");
        primitiveType.put("int", "I");
        primitiveType.put("long", "J");
        primitiveType.put("float", "F");
        primitiveType.put("double", "D");
        return primitiveType.get(this.name);
    }

    /**
     * Class Init Methods
     */

    //if in multi-thread, jclass need a initstate lock
    private void initStart(JClass clazz) {
        clazz.initState = InitState.BUSY;
    }

    private void initSucceed(JClass clazz) {
        clazz.initState = InitState.SUCCESS;
    }

    private void initFail() {
        this.initState = InitState.FAIL;
    }

    public void initClass(JThread thread, JClass clazz) {
        initStart(clazz);
        scheduleClinit(thread, clazz);
        initSuperClass(thread, clazz);
        initSucceed(clazz);
    }

    private void scheduleClinit(JThread thread, JClass clazz) {
        Method clinit = clazz.getClinitMethod();
        if (clinit != null) {
            StackFrame frame = new StackFrame(thread, clinit, clinit.getMaxStack(), clinit.getMaxLocal());
            thread.pushFrame(frame);
        }
    }

    private void initSuperClass(JThread thread, JClass clazz) {
        if (!clazz.isInterface()) {
            JClass superClass = clazz.getSuperClass();
            if (superClass != null && superClass.getInitState() == InitState.PREPARED) {
                initClass(thread, superClass);
            }
        }
    }

    /**
     * search method in class and its superclass
     *
     * @return
     */
    private Method getMethodInClass(String name, String descriptor, boolean isStatic) {
        JClass clazz = this;
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            if (m.getDescriptor().equals(descriptor)
                    && m.getName().equals(name)
                    && m.isStatic() == isStatic) {
                return m;
            }
        }
        return null;
    }

    private Method getClinitMethod() {
        return getMethodInClass("<clinit>", "()V", true);
    }

    public Method getMainMethod() {
        return getMethodInClass("main", "([Ljava/lang/String;)V", true);
    }


    /**
     * Get extra Info
     */

    public String getPackageName() {
        int index = name.lastIndexOf('/');
        if (index >= 0) return name.substring(0, index);
        else return "";
    }

    public boolean isPublic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PUBLIC);
    }

    public boolean isInterface() {
        return 0 != (this.accessFlags & AccessFlags.ACC_INTERFACE);
    }

    public boolean isAbstract() {
        return 0 != (this.accessFlags & AccessFlags.ACC_ABSTRACT);
    }

    public boolean isAccSuper() {
        return 0 != (this.accessFlags & AccessFlags.ACC_SUPER);
    }

    public boolean isArray() {
        return this.name.charAt(0) == '[';
    }

    public boolean isJObjectClass() {
        return this.name.equals("java/lang/Object");
    }

    public boolean isJlCloneable() {
        return this.name.equals("java/lang/Cloneable");
    }

    public boolean isJIOSerializable() {
        return this.name.equals("java/io/Serializable");
    }

    public boolean isAccessibleTo(JClass caller) {
        boolean isPublic = isPublic();
        boolean inSamePackage = this.getPackageName().equals(caller.getPackageName());
        return isPublic || inSamePackage;
    }

    //refer to jvm8 6.5 instanceof inst
    public boolean isAssignableFrom(JClass other) {
        JClass s = other;
        JClass t = this;
        if (s == t) return true;
        if (!s.isArray()) {
            if (!s.isInterface()) {
                if (!t.isInterface()) {
                    return s.isSubClassOf(t);
                } else {
                    return s.isImplementOf(t);
                }
            } else {
                if (!t.isInterface()) {
                    return t.isJObjectClass();
                } else {
                    return t.isSuperInterfaceOf(s);
                }
            }
        } else {
            if (!t.isArray()) {
                if (!t.isInterface()) {
                    return t.isJObjectClass();
                } else {
                    return t.isJIOSerializable() || t.isJlCloneable();
                }
            } else {
                JClass sc = s.getComponentClass();
                JClass tc = t.getComponentClass();
                return sc == tc || t.isJIOSerializable();
            }
        }
    }

    private boolean isSubClassOf(JClass otherClass) {
        JClass superClass = this.getSuperClass();
        while (superClass != null) {
            if (superClass == otherClass) return true;
            superClass = superClass.getSuperClass();
        }
        return false;
    }

    private boolean isImplementOf(JClass otherInterface) {
        JClass superClass = this;
        while (superClass != null) {
            for (JClass i : this.getInterfaces()) {
                if (i == otherInterface || i.isSubInterfaceOf(otherInterface)) return true;
            }
            superClass = this.getSuperClass();
        }
        return false;
    }

    private boolean isSubInterfaceOf(JClass otherInterface) {
        JClass[] superInterfaces = this.getInterfaces();
        for (JClass i : superInterfaces) {
            if (i == otherInterface || i.isSubInterfaceOf(otherInterface)) return true;
        }
        return false;
    }

    private boolean isSuperInterfaceOf(JClass otherInterface) {
        return otherInterface.isSubInterfaceOf(this);
    }
}
