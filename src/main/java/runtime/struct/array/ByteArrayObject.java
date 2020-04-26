package runtime.struct.array;

import lombok.Data;
import runtime.struct.ArrayObject;

@Data
public class ByteArrayObject extends ArrayObject {
    private byte[] array;

    public ByteArrayObject(int len, String type) {
        super(len, type);
        array = new byte[len];
    }
}
