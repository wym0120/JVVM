package com.njuse.seecjvm.instructions.store;

import com.njuse.seecjvm.instructions.base.Index8Instruction;
import com.njuse.seecjvm.runtime.StackFrame;

public class DSTORE extends Index8Instruction {
    @Override
    public void execute(StackFrame frame) {
        double val = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(index, val);
    }
}
