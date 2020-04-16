package instructions.load;

import instructions.base.NoOperandsInstruction;

import java.util.Arrays;

public abstract class LOAD_N extends NoOperandsInstruction {
    protected int index;
    private static int[] valid = {0,1,2,3};
    public static void checkIndex(int i){
        assert (i >= valid[0] && i <= valid[valid.length - 1]);
    }
}
