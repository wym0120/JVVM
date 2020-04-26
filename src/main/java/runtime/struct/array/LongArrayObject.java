package runtime.struct.array;

import lombok.Data;
import runtime.struct.ArrayObject;

@Data
public class LongArrayObject extends ArrayObject {
    private long[] array;

    public LongArrayObject(int len, String type) {
        super(len, type);
        array = new long[len];
    }
}