package memory.jclass;

import classloader.classfileparser.MethodInfo;
import classloader.classfileparser.attribute.CodeAttribute;
import execution.Decoder;
import instructions.base.Instruction;
import lombok.Data;

import java.nio.ByteBuffer;
import java.util.ArrayList;


@Data
public class Method extends ClassMember {
    private int maxStack;
    private int maxLocal;
    private int argc;
    private byte[] code;
    private ArrayList<String> instList;
    boolean parsed = false;

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

    public void parseCode() {
        ByteBuffer codeReader = ByteBuffer.wrap(code);
        int position = 0;
        codeReader.position(position);
        int size = code.length;
        instList = new ArrayList<>();
        while (position <= size - 1) {
            int opcode = codeReader.get() & 0xff;
            Instruction instruction = Decoder.decode(opcode);
            instruction.fetchOperands(codeReader);
            instList.add(position + " " + instruction.toString());
            position = codeReader.position();
        }
        parsed = true;
    }

}
