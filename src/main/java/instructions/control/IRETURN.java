package instructions.control;

import instructions.base.NoOperandsInstruction;
import runtime.JThread;
import runtime.StackFrame;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-03-09
 */
public class IRETURN extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        int value = frame.getOperandStack().popInt();
        JThread thread = frame.getThread();
        thread.popFrame();
        thread.getTopFrame().getOperandStack().pushInt(value);

        //TODO HOW to change PC??

    }
}
