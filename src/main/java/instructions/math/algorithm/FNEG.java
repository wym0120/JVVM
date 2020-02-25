package instructions.math.algorithm;

import instructions.base.NoOperandsInstruction;
import runtime.OperandStack;
import runtime.StackFrame;

public class FNEG extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        float val = stack.popFloat();
        val = -val;
        stack.pushFloat(val);
    }
}
