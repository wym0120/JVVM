package com.njuse.seecjvm.instructions.store;

import com.njuse.seecjvm.runtime.StackFrame;

public class LSTORE_N extends STORE_N {
    public LSTORE_N(int index) {
        checkIndex(index);
        this.index = index;
    }

    @Override
    public void execute(StackFrame frame) {
        long val = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(index, val);
    }
}
