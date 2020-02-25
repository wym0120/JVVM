package instructions.math.algorithm;

import instructions.base.NoOperandsInstruction;
import runtime.OperandStack;
import runtime.StackFrame;

public class DNEG extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        double val = stack.popDouble();
        val = -val;
        stack.pushDouble(val);
    }
}
