package instructions.store;

import instructions.base.Index8Instruction;
import runtime.StackFrame;

public class LSTORE extends Index8Instruction {
    @Override
    public void execute(StackFrame frame) {
        long val = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(index,val);
    }
}
