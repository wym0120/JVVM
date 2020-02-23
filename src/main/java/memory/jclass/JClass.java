package memory.jclass;

import classloader.ClassFile;
import classloader.FieldInfo;
import classloader.MethodInfo;
import classloader.constantpool.ConstantPool;
import memory.runtimeConstantPool.RuntimeConstantPool;
import runtime.Vars;

public class JClass {
    private short accessFlags;
    private String name;
    private String superClassName;
    private String[] interfaceNames;
    private RuntimeConstantPool runtimeConstantPool;
    private Field[] fields;
    private Method[] methods;
    private ClassLoader classLoader;
    private JClass superClass;
    private JClass[] interfaces;
    private int instanceSlotCount;
    private int staticSlotCount;
    private Vars staticVars;

    public JClass(ClassFile classFile){
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

    private Field[] parseFields(FieldInfo[] info){
        int len = info.length;
        fields = new Field[len];
        for(int i = 0;i<len;i++){
            fields[i] = new Field(info[i],this);
        }
        return fields;
    }

    private Method[] parseMethods(MethodInfo[] info){
        int len = info.length;
        methods = new Method[len];
        for(int i = 0;i<len;i++){
            methods[i] = new Method(info[i],this);
        }
        return methods;
    }

    private RuntimeConstantPool parseRuntimeConstantPool(ConstantPool cp){
        return null;
    }
}
