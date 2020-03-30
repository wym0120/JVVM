package instructions.references;

import instructions.base.NoOperandsInstruction;
import runtime.OperandStack;
import runtime.StackFrame;
import runtime.struct.ArrayObject;
import runtime.struct.JObject;

public class ARRAYLENGTH extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        JObject ref = stack.popObjectRef();
        if(ref == null || ref.isNull())throw new NullPointerException();
        stack.pushInt(((ArrayObject)ref).getLen());
    }
}
