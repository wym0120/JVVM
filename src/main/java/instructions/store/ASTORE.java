package instructions.store;

import instructions.base.Index8Instruction;
import runtime.StackFrame;
import runtime.struct.JObject;

public class ASTORE extends Index8Instruction {
    @Override
    public void execute(StackFrame frame) {
        JObject ref = frame.getOperandStack().popObjectRef();
        frame.getLocalVars().setObjectRef(index,ref);
    }
}
