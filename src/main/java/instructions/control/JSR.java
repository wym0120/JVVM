package instructions.control;

import instructions.base.BranchInstruction;
import runtime.StackFrame;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-03-10
 */
@Deprecated
public class JSR extends BranchInstruction {
    @Override
    public void execute(StackFrame frame) {
        int nextPC = frame.getNextPC();
//        frame.getOperandStack()
        int branchPC = nextPC - 3 + super.offset;
        frame.setNextPC(branchPC);
    }

}
