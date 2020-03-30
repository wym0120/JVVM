package runtime.struct.array;

import lombok.Data;
import runtime.struct.ArrayObject;

@Data
public class ByteArrayObject extends ArrayObject {
    private byte[] array;

    public ByteArrayObject(int len){
        super(len);
        array = new byte[len];
    }
}
