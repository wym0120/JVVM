package runtime.struct.array;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import runtime.struct.ArrayObject;

@Getter
@Setter
public class ShortArrayObject extends ArrayObject {
    private short[] array;

    public ShortArrayObject(int len, String type) {
        super(len, type);
        array = new short[len];
    }
}