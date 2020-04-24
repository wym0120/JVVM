package instructions.stack;

import instructions.base.NoOperandsInstruction;
import runtime.OperandStack;
import runtime.StackFrame;
import runtime.struct.Slot;

public class DUP extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot = stack.popSlot();
        stack.pushSlot(slot.clone());
        stack.pushSlot(slot.clone());
    }
}
