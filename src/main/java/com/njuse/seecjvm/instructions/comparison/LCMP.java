package com.njuse.seecjvm.instructions.comparison;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.StackFrame;

public class LCMP extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        long value2 = frame.getOperandStack().popLong();
        long value1 = frame.getOperandStack().popLong();
        frame.getOperandStack().pushInt(Long.compare(value1, value2));
    }
}
