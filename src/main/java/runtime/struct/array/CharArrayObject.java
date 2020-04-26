package runtime.struct.array;

import lombok.Data;
import runtime.struct.ArrayObject;

@Data
public class CharArrayObject extends ArrayObject {
    private char[] array;

    public CharArrayObject(int len, String type) {
        super(len, type);
        array = new char[len];
    }
}