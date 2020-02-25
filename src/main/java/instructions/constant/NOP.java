package instructions.constant;

import instructions.base.NoOperandsInstruction;
import runtime.StackFrame;

public class NOP extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        //do nothing
    }
}
