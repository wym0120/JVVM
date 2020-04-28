package instructions.constant;

import instructions.base.NoOperandsInstruction;
import runtime.StackFrame;
import runtime.struct.NullObject;

public class ACONST_NULL extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushObjectRef(new NullObject());
    }
}
