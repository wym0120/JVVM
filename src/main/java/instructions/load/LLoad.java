package instructions.load;

import instructions.base.Index8Instruction;
import instructions.base.Instruction;
import runtime.StackFrame;

public class LLoad extends Index8Instruction {
    @Override
    public void execute(StackFrame frame) {
        long val = frame.getLocalVars().getLong(index);
        frame.getOperandStack().pushLong(val);
    }
}
