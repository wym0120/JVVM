package com.njuse.seecjvm.instructions.load;

import com.njuse.seecjvm.instructions.base.Index8Instruction;
import com.njuse.seecjvm.runtime.StackFrame;

public class ILOAD extends Index8Instruction {

    @Override
    public void execute(StackFrame frame) {
        int val = frame.getLocalVars().getInt(index);
        frame.getOperandStack().pushInt(val);
    }
}
