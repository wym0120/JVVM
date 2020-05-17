package com.njuse.seecjvm.instructions.store;

import com.njuse.seecjvm.instructions.base.Index8Instruction;
import com.njuse.seecjvm.runtime.StackFrame;

public class FSTORE extends Index8Instruction {
    @Override
    public void execute(StackFrame frame) {
        float val = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(index, val);
    }
}
