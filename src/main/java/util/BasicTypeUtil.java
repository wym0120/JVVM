package util;

public class BasicTypeUtil {

    public static Integer float2int(float value){
        return Float.floatToIntBits(value);
    }

    public static Float int2float(int value){
        return Float.intBitsToFloat(value);
    }
}
