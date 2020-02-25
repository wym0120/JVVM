package instructions.store;

import instructions.base.NoOperandsInstruction;

import java.util.Arrays;

public abstract class STORE_N extends NoOperandsInstruction {
    protected int index;
    private static int[] valid = {0,1,2,3};
    public static void checkIndex(int i){
        assert (Arrays.asList(valid).contains(i));
    }
}