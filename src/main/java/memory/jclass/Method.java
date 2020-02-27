package memory.jclass;

import classloader.MethodInfo;
import classloader.attribute.CodeAttribute;

public class Method {
    private short accessFlags;
    private String name;
    private String descriptor;
    private JClass clazz;
    private int maxStack;
    private int maxLocal;
    private byte[] code;


    public Method(MethodInfo info, JClass clazz) {
        this.clazz = clazz;
        accessFlags = info.getAccessFlags();
        name = info.getName();
        descriptor = info.getDescriptor();
        CodeAttribute codeAttribute = info.getCodeAttribute();
        maxLocal = codeAttribute.getMaxLocal();
        maxStack = codeAttribute.getMaxStack();
        code = codeAttribute.getCode();
    }

    public boolean isPublic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PUBLIC);
    }
}
