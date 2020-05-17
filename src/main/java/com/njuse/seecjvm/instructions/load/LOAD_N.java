package com.njuse.seecjvm.instructions.load;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;

public abstract class LOAD_N extends NoOperandsInstruction {
    protected int index;
    private static int[] valid = {0, 1, 2, 3};

    public static void checkIndex(int i) {
        assert (i >= valid[0] && i <= valid[valid.length - 1]);
    }

    @Override
    public String toString() {
        String suffix = index + "";
        return this.getClass().getSimpleName().replace("N", suffix);
    }
}
