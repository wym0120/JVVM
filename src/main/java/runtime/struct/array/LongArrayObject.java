package runtime.struct.array;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import runtime.struct.ArrayObject;

@Getter
@Setter
public class LongArrayObject extends ArrayObject {
    private long[] array;

    public LongArrayObject(int len, String type) {
        super(len, type);
        array = new long[len];
    }
}