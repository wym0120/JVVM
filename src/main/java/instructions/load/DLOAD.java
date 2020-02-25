package instructions.load;

import instructions.base.Index8Instruction;
import runtime.StackFrame;

public class DLOAD extends Index8Instruction {
    @Override
    public void execute(StackFrame frame) {
        double val = frame.getLocalVars().getDouble(index);
        frame.getOperandStack().pushDouble(val);
    }
}
