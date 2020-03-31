package instructions.store;

import instructions.base.NoOperandsInstruction;
import runtime.OperandStack;
import runtime.StackFrame;
import runtime.struct.array.ByteArrayObject;

public class BASTORE extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        byte val = (byte) stack.popInt();
        int index = stack.popInt();
        ByteArrayObject arrRef = (ByteArrayObject) stack.popObjectRef();
        if (arrRef == null) throw new NullPointerException();
        if (index < 0 || index >= arrRef.getLen()) throw new ArrayIndexOutOfBoundsException();
        arrRef.getArray()[index] = val;
    }
}
