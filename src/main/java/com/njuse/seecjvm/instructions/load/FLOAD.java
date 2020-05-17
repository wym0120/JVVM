package com.njuse.seecjvm.instructions.load;

import com.njuse.seecjvm.instructions.base.Index8Instruction;
import com.njuse.seecjvm.runtime.StackFrame;

public class FLOAD extends Index8Instruction {

    @Override
    public void execute(StackFrame frame) {
        float val = frame.getLocalVars().getFloat(index);
        frame.getOperandStack().pushFloat(val);
    }
}
