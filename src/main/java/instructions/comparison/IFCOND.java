package instructions.comparison;

import instructions.base.BranchInstruction;
import runtime.StackFrame;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-03-09
 */
public abstract class IFCOND extends BranchInstruction {
    @Override
    public void execute(StackFrame frame) {
        int value = frame.getOperandStack().popInt();
        if (condition(value)) {
            int nextPC = frame.getNextPC();
            int branchPC = nextPC - 3 + super.offset;
            frame.setNextPC(branchPC);
        }
    }

    protected abstract boolean condition(int value);

}
