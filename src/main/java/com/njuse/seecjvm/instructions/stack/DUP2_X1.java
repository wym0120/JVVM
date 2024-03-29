package com.njuse.seecjvm.instructions.stack;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.struct.Slot;

public class DUP2_X1 extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();
        Slot slot3 = stack.popSlot();
        stack.pushSlot(slot2.clone());
        stack.pushSlot(slot1.clone());
        stack.pushSlot(slot3.clone());
        stack.pushSlot(slot2.clone());
        stack.pushSlot(slot1.clone());
    }
}
