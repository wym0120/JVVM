package instructions.store;

import instructions.base.NoOperandsInstruction;
import runtime.OperandStack;
import runtime.StackFrame;
import runtime.struct.array.CharArrayObject;

public class CASTORE extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        char val = (char) stack.popInt();
        int index = stack.popInt();
        CharArrayObject arrRef = (CharArrayObject) stack.popObjectRef();
        if (arrRef == null) throw new NullPointerException();
        if (index < 0 || index >= arrRef.getLen()) throw new ArrayIndexOutOfBoundsException();
        arrRef.getArray()[index] = val;
    }
}
