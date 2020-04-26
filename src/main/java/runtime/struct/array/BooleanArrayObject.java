package runtime.struct.array;

import lombok.Data;
import runtime.struct.ArrayObject;

@Data
public class BooleanArrayObject extends ArrayObject {
    private boolean[] array;

    public BooleanArrayObject(int len, String type) {
        super(len, type);
        array = new boolean[len];
    }
}