package memory.jclass;

import classloader.classfileparser.MethodInfo;
import classloader.classfileparser.attribute.CodeAttribute;
import lombok.Data;

@Data
public class Method extends ClassMember {
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

}
