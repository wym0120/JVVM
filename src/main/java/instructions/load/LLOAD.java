package instructions.load;

import instructions.base.Index8Instruction;
import runtime.StackFrame;

public class LLOAD extends Index8Instruction {
    @Override
    public void execute(StackFrame frame) {
        long val = frame.getLocalVars().getLong(index);
        frame.getOperandStack().pushLong(val);
    }
}
