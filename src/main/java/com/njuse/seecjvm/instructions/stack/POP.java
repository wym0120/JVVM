package com.njuse.seecjvm.instructions.stack;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.StackFrame;

public class POP extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().popSlot();
    }
}
