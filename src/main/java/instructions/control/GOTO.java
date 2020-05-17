package instructions.control;

import instructions.base.BranchInstruction;
import runtime.StackFrame;

public class GOTO extends BranchInstruction {
    @Override
    public void execute(StackFrame frame) {
        int branchPC = frame.getNextPC() - 3 + super.offset;// 3 = opcode + signed short offset
        frame.setNextPC(branchPC);
    }
}
