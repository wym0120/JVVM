package instructions.load;

import instructions.base.Index8Instruction;
import runtime.StackFrame;

public class FLOAD extends Index8Instruction {

    @Override
    public void execute(StackFrame frame) {
        float val = frame.getLocalVars().getFloat(index);
        frame.getOperandStack().pushFloat(val);
    }
}
