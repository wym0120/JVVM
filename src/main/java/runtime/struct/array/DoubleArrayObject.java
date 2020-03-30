package runtime.struct.array;

import lombok.Data;
import runtime.struct.ArrayObject;

@Data
public class DoubleArrayObject extends ArrayObject {
    private double[] array;

    public DoubleArrayObject(int len){
        super(len);
        array = new double[len];
    }
}