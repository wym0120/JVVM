package memory.jclass;

import classloader.classfileparser.ClassFile;
import classloader.classfileparser.FieldInfo;
import classloader.classfileparser.MethodInfo;
import classloader.classfileparser.constantpool.ConstantPool;
import classloader.classfilereader.classpath.EntryType;
import lombok.Data;
import memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import runtime.Vars;

@Data
public class JClass {
    private short accessFlags;
    private String name;
    private String superClassName;
    private String[] interfaceNames;
    private RuntimeConstantPool runtimeConstantPool;
    private Field[] fields;
    private Method[] methods;
    //    private ClassLoader classLoader;
    private EntryType loadEntryType;
    private JClass superClass;
    private JClass[] interfaces;
    private int instanceSlotCount;
    private int staticSlotCount;
    private Vars staticVars;

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

    public boolean isPublic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PUBLIC);
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

    public boolean isAccessibleTo(JClass caller) {
        boolean isPublic = isPublic();
        boolean inSamePackage = this.getPackageName().equals(caller.getPackageName());
        return isPublic || inSamePackage;
    }

    public String getPackageName() {
        int index = name.lastIndexOf('/');
        if (index >= 0) return name.substring(0, index);
        else return "";
    }
}
