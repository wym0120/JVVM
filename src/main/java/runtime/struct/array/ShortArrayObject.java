package runtime.struct.array;

import lombok.Data;
import runtime.struct.ArrayObject;

@Data
public class ShortArrayObject extends ArrayObject {
    private short[] array;

    public ShortArrayObject(int len, String type) {
        super(len, type);
        array = new short[len];
    }
}