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
public class DRETURN extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        double value = frame.getOperandStack().popDouble();
        JThread thread = frame.getThread();
        thread.popFrame();
        thread.getTopFrame().getOperandStack().pushDouble(value);
    }
}
