package instructions.comparison;

import instructions.base.BranchInstruction;
import runtime.StackFrame;
import runtime.struct.JObject;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-03-10
 */
public abstract class IF_ACMPCOND extends BranchInstruction {
    @Override
    public void execute(StackFrame frame) {
        JObject value2 = frame.getOperandStack().popObjectRef();
        JObject value1 = frame.getOperandStack().popObjectRef();

        if (condition(value1, value2)) {
            int nextPC = frame.getNextPC();
            int branchNext = nextPC - 3 + offset;
            frame.setNextPC(branchNext);
        }
    }

    protected abstract boolean condition(JObject v1, JObject v2);
}
