package instructions.store;

import instructions.base.Index8Instruction;
import runtime.StackFrame;

public class FSTORE extends Index8Instruction {
    @Override
    public void execute(StackFrame frame) {
        float val = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(index,val);
    }
}
