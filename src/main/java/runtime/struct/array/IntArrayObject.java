package runtime.struct.array;

import lombok.Data;
import runtime.struct.ArrayObject;

@Data
public class IntArrayObject extends ArrayObject {
    private int[] array;

    public IntArrayObject(int len){
        super(len);
        array = new int[len];
    }
}
