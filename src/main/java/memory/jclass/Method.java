package memory.jclass;

import classloader.classfileparser.MethodInfo;
import classloader.classfileparser.attribute.CodeAttribute;
import lombok.Data;

@Data
public class Method extends ClassMember {
    private int maxStack;
    private int maxLocal;
    private int argc;
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
        argc = calculateArgcFromDescriptor(descriptor);
    }

    private int calculateArgcFromDescriptor(String descriptor) {
        char[] chars = descriptor.toCharArray();
        int maxIndex = descriptor.lastIndexOf(')');
        assert maxIndex != -1;
        int idx = descriptor.indexOf('(');
        assert idx != -1;
        //skip the index of '('
        idx++;
        int cnt = 0;
        while (idx + 1 <= maxIndex) {
            switch (chars[idx++]) {
                case 'J':
                case 'D':
                    cnt++;
                    //fall through
                case 'F':
                case 'I':
                case 'B':
                case 'C':
                case 'S':
                case 'Z':
                    cnt++;
                    break;
                case 'L':
                    cnt++;
                    while (idx < maxIndex && chars[idx++] != ';') ;
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
