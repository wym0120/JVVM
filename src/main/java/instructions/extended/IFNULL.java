package instructions.extended;

import instructions.base.BranchInstruction;
import runtime.StackFrame;
import runtime.struct.JObject;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-03-09
 */
public class IFNULL extends BranchInstruction {
    @Override
    public void execute(StackFrame frame) {
        JObject ref = frame.getOperandStack().popObjectRef();
        if (ref.isNull()) {
            int branchPC = frame.getNextPC() - 3 + super.offset;
            frame.setNextPC(branchPC);
        }
    }
}
