package instructions.constant;

import instructions.base.NoOperandsInstruction;
import runtime.StackFrame;

import java.util.Arrays;

public class DCONST_N extends NoOperandsInstruction {
    private double val;
    private static double[] valid = {0.0,1.0};

    public DCONST_N(double val){
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
}
