package runtime.struct.array;

import lombok.Data;
import runtime.struct.ArrayObject;

@Data
public class FloatArrayObject extends ArrayObject {
    private float[] array;

    public FloatArrayObject(int len){
        super(len);
        array = new float[len];
    }
}
