package instructions.constant;

import instructions.base.NoOperandsInstruction;
import runtime.StackFrame;

import java.util.Arrays;

public class FCONST_N extends NoOperandsInstruction {
    private float val;
    private static float[] valid = {0.0f,1.0f,2.0f};

    public FCONST_N(float val){
        if (!Arrays.asList(valid).contains(val))throw new IllegalArgumentException();
        this.val = val;
    }
    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushFloat(val);
    }
}
