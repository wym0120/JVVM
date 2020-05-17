package instructions.comparison;

import instructions.base.BranchInstruction;
import runtime.StackFrame;

public abstract class IF_ICMPCOND extends BranchInstruction {
    @Override
    public void execute(StackFrame frame) {
        int value2 = frame.getOperandStack().popInt();
        int value1 = frame.getOperandStack().popInt();
        if (condition(value1, value2)) {
            int nextPC = frame.getNextPC();
            int branchNext = nextPC - 3 + offset;
            frame.setNextPC(branchNext);
        }
    }

    protected abstract boolean condition(int v1, int v2);
}
