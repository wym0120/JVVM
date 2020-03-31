package instructions.store;

import instructions.base.NoOperandsInstruction;
import runtime.OperandStack;
import runtime.StackFrame;
import runtime.struct.array.FloatArrayObject;

public class FASTORE extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        float val = stack.popFloat();
        int index = stack.popInt();
        FloatArrayObject arrRef = (FloatArrayObject) stack.popObjectRef();
        if (arrRef == null) throw new NullPointerException();
        if (index < 0 || index >= arrRef.getLen()) throw new ArrayIndexOutOfBoundsException();
        arrRef.getArray()[index] = val;
    }
}
