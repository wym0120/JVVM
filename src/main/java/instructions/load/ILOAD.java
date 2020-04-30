package instructions.load;

import instructions.base.Index8Instruction;
import runtime.StackFrame;

public class ILOAD extends Index8Instruction {

    @Override
    public void execute(StackFrame frame) {
        int val = frame.getLocalVars().getInt(index);
        frame.getOperandStack().pushInt(val);
    }
}
