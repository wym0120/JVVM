package runtime.struct.array;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import runtime.struct.ArrayObject;

@Getter
@Setter
public class ByteArrayObject extends ArrayObject {
    private byte[] array;

    public ByteArrayObject(int len, String type) {
        super(len, type);
        array = new byte[len];
    }
}
