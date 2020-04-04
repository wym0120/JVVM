package instructions.constant;

import instructions.base.NoOperandsInstruction;
import runtime.StackFrame;

import java.util.Arrays;

public class LCONST_N  extends NoOperandsInstruction {
    private long val;
    private static long[] valid = {0L,1L};

    public LCONST_N(int val){
        if (!Arrays.asList(valid).contains(val))throw new IllegalArgumentException();
        this.val = val;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushLong(val);
    }
}
