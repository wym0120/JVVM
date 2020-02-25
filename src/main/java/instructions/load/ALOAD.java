package instructions.load;

import instructions.base.Index8Instruction;
import runtime.StackFrame;
import runtime.struct.JObject;

public class ALOAD extends Index8Instruction {
    @Override
    public void execute(StackFrame frame) {
        JObject ref = frame.getLocalVars().getObjectRef(index);
        frame.getOperandStack().pushObjectRef(ref);
    }
}
