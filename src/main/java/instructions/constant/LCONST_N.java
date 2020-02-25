package instructions.constant;

import java.util.Arrays;

public class LCONST_N {
    private long val;
    private static long[] valid = {0L,1L};

    public LCONST_N(int val){
        if (!Arrays.asList(valid).contains(val))throw new IllegalArgumentException();
        this.val = val;
    }
}
