package instructions.constant;

import instructions.base.NoOperandsInstruction;
import runtime.StackFrame;

import java.util.Arrays;

public class DCONST_N extends NoOperandsInstruction {
    private double val;
    private static double[] valid = {0.0,1.0};

    public DCONST_N(double val){
        if (!Arrays.asList(valid).contains(val))throw new IllegalArgumentException();
        this.val = val;
    }
    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushDouble(val);
    }
}
