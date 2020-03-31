package instructions.store;

import instructions.base.NoOperandsInstruction;
import runtime.OperandStack;
import runtime.StackFrame;
import runtime.struct.array.IntArrayObject;

public class IASTORE extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int val = stack.popInt();
        int index = stack.popInt();
        IntArrayObject arrRef = (IntArrayObject) stack.popObjectRef();
        if (arrRef == null) throw new NullPointerException();
        if (index < 0 || index >= arrRef.getLen()) throw new ArrayIndexOutOfBoundsException();
        arrRef.getArray()[index] = val;
    }
}
