package instructions.stack;

import instructions.base.NoOperandsInstruction;
import runtime.StackFrame;

public class POP extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().popSlot();
    }
}
