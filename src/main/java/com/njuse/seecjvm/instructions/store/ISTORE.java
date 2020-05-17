package com.njuse.seecjvm.instructions.store;

import com.njuse.seecjvm.instructions.base.Index8Instruction;
import com.njuse.seecjvm.runtime.StackFrame;

public class ISTORE extends Index8Instruction {
    @Override
    public void execute(StackFrame frame) {
        int val = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(index, val);
    }
}
