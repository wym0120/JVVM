package runtime.struct.array;

import lombok.Getter;
import lombok.Setter;
import runtime.struct.ArrayObject;

@Getter
@Setter
public class IntArrayObject extends ArrayObject {
    private int[] array;

    public IntArrayObject(int len, String type) {
        super(len, type);
        array = new int[len];
    }
}
