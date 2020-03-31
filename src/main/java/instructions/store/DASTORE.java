package instructions.store;

import instructions.base.NoOperandsInstruction;
import runtime.OperandStack;
import runtime.StackFrame;
import runtime.struct.array.DoubleArrayObject;

public class DASTORE extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        double val = stack.popDouble();
        int index = stack.popInt();
        DoubleArrayObject arrRef = (DoubleArrayObject) stack.popObjectRef();
        if (arrRef == null) throw new NullPointerException();
        if (index < 0 || index >= arrRef.getLen()) throw new ArrayIndexOutOfBoundsException();
        arrRef.getArray()[index] = val;
    }
}
