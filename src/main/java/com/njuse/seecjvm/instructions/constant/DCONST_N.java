package com.njuse.seecjvm.instructions.constant;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.StackFrame;

public class DCONST_N extends NoOperandsInstruction {
    private double val;
    private static double[] valid = {0.0, 1.0};

    public DCONST_N(double val) {
        for (double n : valid) {
            if (n == val) {
                this.val = val;
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushDouble(val);
    }

    @Override
    public String toString() {
        String suffix = (int) val + "";
        String simpleName = this.getClass().getSimpleName();
        return simpleName.substring(0, simpleName.length() - 1) + suffix;
    }
}
