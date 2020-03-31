package instructions.load;

import instructions.base.NoOperandsInstruction;
import runtime.OperandStack;
import runtime.StackFrame;
import runtime.struct.array.CharArrayObject;

public class CALOAD extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        CharArrayObject arrRef = (CharArrayObject) stack.popObjectRef();
        if (arrRef == null) throw new NullPointerException();
        if (checkIndex(arrRef.getLen(), index)) {
            stack.pushInt((arrRef.getArray())[index]);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private boolean checkIndex(int len, int index) {
        return !(index < 0 || index >= len);
    }
}
