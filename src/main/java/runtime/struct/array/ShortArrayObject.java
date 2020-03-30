package runtime.struct.array;

import lombok.Data;
import runtime.struct.ArrayObject;

@Data
public class ShortArrayObject extends ArrayObject {
    private short[] array;

    public ShortArrayObject(int len){
        super(len);
        array = new short[len];
    }
}