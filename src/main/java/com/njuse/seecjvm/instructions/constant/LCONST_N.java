package com.njuse.seecjvm.instructions.constant;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.StackFrame;

public class LCONST_N extends NoOperandsInstruction {
    private long val;
    private static long[] valid = {0L, 1L};

    public LCONST_N(int val) {
        if (!(val >= valid[0] && val <= valid[valid.length - 1])) throw new IllegalArgumentException();
        this.val = val;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushLong(val);
    }

    @Override
    public String toString() {
        String simpleName = this.getClass().getSimpleName();
        return simpleName.substring(0, simpleName.length() - 1) + val;
    }
}
