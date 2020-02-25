package instructions.stack;

import instructions.base.NoOperandsInstruction;
import runtime.OperandStack;
import runtime.StackFrame;
import runtime.struct.Slot;

public class DUP2_X1 extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();
        Slot slot3 = stack.popSlot();
        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
        stack.pushSlot(slot3);
        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
    }
}
