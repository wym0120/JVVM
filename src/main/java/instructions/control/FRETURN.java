package instructions.control;

import instructions.base.NoOperandsInstruction;
import runtime.JThread;
import runtime.StackFrame;

public class FRETURN extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        float value = frame.getOperandStack().popFloat();
        JThread thread = frame.getThread();
        thread.popFrame();
        thread.getTopFrame().getOperandStack().pushFloat(value);
    }
}
