package com.njuse.seecjvm.instructions.load;

import com.njuse.seecjvm.runtime.StackFrame;

public class FLOAD_N extends LOAD_N {
    public FLOAD_N(int index) {
        checkIndex(index);
        this.index = index;
    }

    @Override
    public void execute(StackFrame frame) {
        float val = frame.getLocalVars().getFloat(index);
        frame.getOperandStack().pushFloat(val);
    }
}
