package com.njuse.seecjvm.instructions.constant;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.StackFrame;

public class ICONST_N extends NoOperandsInstruction {
    private int val;
    private static int[] valid = {-1, 0, 1, 2, 3, 4, 5};

    public ICONST_N(int val) {
        if (!(val >= valid[0] && val <= valid[valid.length - 1])) throw new IllegalArgumentException();
        this.val = val;
    }


    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushInt(val);
    }

    @Override
    public String toString() {
        String suffix = (val == -1) ? "M1" : "" + val;
        String simpleName = this.getClass().getSimpleName();
        return simpleName.substring(0, simpleName.length() - 1) + suffix;
    }
}
