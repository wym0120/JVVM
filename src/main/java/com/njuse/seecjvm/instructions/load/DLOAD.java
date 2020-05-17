package com.njuse.seecjvm.instructions.load;

import com.njuse.seecjvm.instructions.base.Index8Instruction;
import com.njuse.seecjvm.runtime.StackFrame;

public class DLOAD extends Index8Instruction {
    @Override
    public void execute(StackFrame frame) {
        double val = frame.getLocalVars().getDouble(index);
        frame.getOperandStack().pushDouble(val);
    }
}
