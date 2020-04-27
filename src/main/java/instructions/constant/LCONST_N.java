package instructions.constant;

import instructions.base.NoOperandsInstruction;
import runtime.StackFrame;

public class LCONST_N  extends NoOperandsInstruction {
    private long val;
    private static long[] valid = {0L,1L};

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
        return this.getClass().getSimpleName().replace("N", val + "");
    }
}
