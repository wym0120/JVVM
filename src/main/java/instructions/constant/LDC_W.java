package instructions.constant;

import instructions.base.Index16Instruction;
import runtime.StackFrame;

public class LDC_W extends Index16Instruction {
    @Override
    public void execute(StackFrame frame) {
        LDC.loadConstant(frame, index);
    }
}
