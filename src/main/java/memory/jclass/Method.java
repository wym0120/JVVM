package memory.jclass;

import classloader.classfileparser.MethodInfo;
import classloader.classfileparser.attribute.CodeAttribute;
import lombok.Data;

@Data
public class Method extends ClassMember {
    private int maxStack;
    private int maxLocal;
    private int argc;
    private String name;
    private String descriptor;
    private byte[] code;


    public Method(MethodInfo info, JClass clazz) {
        this.clazz = clazz;
        accessFlags = info.getAccessFlags();
        name = info.getName();
        descriptor = info.getDescriptor();
        CodeAttribute codeAttribute = info.getCodeAttribute();
        if (codeAttribute != null) {
            maxLocal = codeAttribute.getMaxLocal();
            maxStack = codeAttribute.getMaxStack();
            code = codeAttribute.getCode();
        }
        name = info.getName();
        descriptor = info.getDescriptor();
        argc = calculateArgcFromDescriptor(descriptor);
    }

    private int calculateArgcFromDescriptor(String descriptor) {
        char[] chars = descriptor.toCharArray();
        int length = descriptor.lastIndexOf(')');
        assert length != -1;
        int idx = descriptor.indexOf('(');
        assert idx != -1;
        int cnt = 0;
        while (idx < length) {
            switch (chars[idx++]) {
                case 'B':
                case 'C':
                case 'D':
                case 'F':
                case 'I':
                case 'J':
                case 'S':
                case 'Z':
                    cnt++;
                    break;
                case 'L':
                    cnt++;
                    while (idx < length && chars[idx++] != ';') ;
                    break;
                case '[':
                    break;
                default:
                    throw new UnsupportedOperationException("Unknown descriptor!");
            }
        }
        return cnt;
    }

}
