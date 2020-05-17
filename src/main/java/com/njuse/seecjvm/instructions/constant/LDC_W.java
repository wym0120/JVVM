package com.njuse.seecjvm.instructions.constant;

import com.njuse.seecjvm.instructions.base.Index16Instruction;
import com.njuse.seecjvm.runtime.StackFrame;

public class LDC_W extends Index16Instruction {
    @Override
    public void execute(StackFrame frame) {
        LDC.loadConstant(frame, index);
    }
}
