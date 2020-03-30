package runtime.struct.array;

import lombok.Data;
import runtime.struct.ArrayObject;

@Data
public class LongArrayObject extends ArrayObject {
    private long[] array;

    public LongArrayObject(int len){
        super(len);
        array = new long[len];
    }
}