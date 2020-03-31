package memory.jclass;

import classloader.ClassLoader;
import classloader.classfileparser.ClassFile;
import classloader.classfileparser.FieldInfo;
import classloader.classfileparser.MethodInfo;
import classloader.classfileparser.constantpool.ConstantPool;
import classloader.classfilereader.classpath.EntryType;
import lombok.Data;
import memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import runtime.Vars;
import runtime.struct.ArrayObject;
import runtime.struct.NonArrayObject;
import runtime.struct.array.*;

import java.util.HashMap;
import java.util.Optional;

@Data
public class JClass {
    private short accessFlags;
    private String name;
    private String superClassName;
    private String[] interfaceNames;
    private RuntimeConstantPool runtimeConstantPool;
    private Field[] fields;
    private Method[] methods;
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
        this.superClassName = classFile.getSuperClassName();
        this.interfaceNames = classFile.getInterfaceNames();
        this.fields = parseFields(classFile.getFields());
        this.methods = parseMethods(classFile.getMethods());
        runtimeConstantPool = parseRuntimeConstantPool(classFile.getConstantPool());
        fields = parseFields(classFile.getFields());
        methods = parseMethods(classFile.getMethods());
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
            case "[Z":return new BooleanArrayObject(len);
            case "[B":return new ByteArrayObject(len);
            case "[C":return new CharArrayObject(len);
            case "[S":return new ShortArrayObject(len);
            case "[I":return new IntArrayObject(len);
            case "[J":return new LongArrayObject(len);
            case "[F":return new FloatArrayObject(len);
            case "[D":return new DoubleArrayObject(len);
            default:return new RefArrayObject(len);
        }
    }

    /**
     * use for anewarray inst
     * @return
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

    public void initStart() {
//        this.initState = InitState.BUSY;
    }

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

    public boolean isAccessibleTo(JClass caller) {
        boolean isPublic = isPublic();
        boolean inSamePackage = this.getPackageName().equals(caller.getPackageName());
        return isPublic || inSamePackage;
    }

    public boolean isAssignableFrom(JClass other) {
        //todo:complete this func after implement array object!!!!!
        JClass s = other;
        JClass t = this;
        if (s == t) return true;
        if (!t.isInterface()) {
            return s.isSubClassOf(t);
        } else {
            return s.isImplementOf(t);
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

}
