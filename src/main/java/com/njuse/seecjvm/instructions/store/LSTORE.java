package com.njuse.seecjvm.instructions.store;

import com.njuse.seecjvm.instructions.base.Index8Instruction;
import com.njuse.seecjvm.runtime.StackFrame;

public class LSTORE extends Index8Instruction {
    @Override
    public void execute(StackFrame frame) {
        long val = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(index, val);
    }
}
