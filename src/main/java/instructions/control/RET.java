package instructions.control;

import instructions.base.Index8Instruction;
import runtime.StackFrame;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-03-10
 */
@Deprecated
public class RET extends Index8Instruction {
    @Override
    public void execute(StackFrame frame) {
        int index = super.index;
//        frame.getLocalVars()
    }
}
