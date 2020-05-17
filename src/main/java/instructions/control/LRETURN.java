package instructions.control;

import instructions.base.NoOperandsInstruction;
import runtime.JThread;
import runtime.StackFrame;

public class LRETURN extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        long value = frame.getOperandStack().popLong();
        JThread thread = frame.getThread();
        thread.popFrame();
        thread.getTopFrame().getOperandStack().pushLong(value);
    }
}
