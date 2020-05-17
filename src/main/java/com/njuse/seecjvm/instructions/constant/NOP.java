package com.njuse.seecjvm.instructions.constant;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.StackFrame;

public class NOP extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        //do nothing
    }
}
