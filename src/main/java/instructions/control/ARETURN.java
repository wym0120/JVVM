package instructions.control;

import instructions.base.NoOperandsInstruction;
import runtime.JThread;
import runtime.StackFrame;
import runtime.struct.JObject;

public class ARETURN extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        JObject ref = frame.getOperandStack().popObjectRef();
        JThread thread = frame.getThread();
        thread.popFrame();
        thread.getTopFrame().getOperandStack().pushObjectRef(ref);
    }
}
