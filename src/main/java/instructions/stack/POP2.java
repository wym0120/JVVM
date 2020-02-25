package instructions.stack;

import instructions.base.NoOperandsInstruction;
import runtime.OperandStack;
import runtime.StackFrame;

public class POP2 extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        stack.popSlot();
        stack.popSlot();
    }
}
