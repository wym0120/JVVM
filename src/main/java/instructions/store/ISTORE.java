package instructions.store;

import instructions.base.Index8Instruction;
import runtime.StackFrame;

public class ISTORE extends Index8Instruction {
    @Override
    public void execute(StackFrame frame) {
        int val = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(index,val);
    }
}
