package instructions.constant;

import instructions.base.NoOperandsInstruction;
import runtime.StackFrame;

import java.util.Arrays;

public class FCONST_N extends NoOperandsInstruction {
    private float val;
    private static float[] valid = {0.0f,1.0f,2.0f};

    public FCONST_N(float val){
        for (float n : valid) {
            if (n == val) {
                this.val = val;
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushFloat(val);
    }
}
