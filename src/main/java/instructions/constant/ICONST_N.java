package instructions.constant;

import instructions.base.NoOperandsInstruction;
import runtime.StackFrame;

import java.util.Arrays;

public class ICONST_N extends NoOperandsInstruction {
    private int val;
    private static int[] valid = {-1,0,1,2,3,4,5};

    public ICONST_N(int val){
        if (!Arrays.asList(valid).contains(val))throw new IllegalArgumentException();
        this.val = val;
    }


    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushInt(val);
    }
}
