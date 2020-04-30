package instructions.control;

import instructions.base.BranchInstruction;
import runtime.StackFrame;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-03-10
 */
public class GOTO extends BranchInstruction {
    @Override
    public void execute(StackFrame frame) {
        int branchPC = frame.getNextPC() - 3 + super.offset;
        frame.setNextPC(branchPC);
    }
}
