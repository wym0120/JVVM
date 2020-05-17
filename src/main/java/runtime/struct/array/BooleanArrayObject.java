package runtime.struct.array;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import runtime.struct.ArrayObject;

@Getter
@Setter
public class BooleanArrayObject extends ArrayObject {
    private boolean[] array;

    public BooleanArrayObject(int len, String type) {
        super(len, type);
        array = new boolean[len];
    }
}