package runtime.struct.array;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import runtime.struct.ArrayObject;

@Getter
@Setter
public class DoubleArrayObject extends ArrayObject {
    private double[] array;

    public DoubleArrayObject(int len, String type) {
        super(len, type);
        array = new double[len];
    }
}