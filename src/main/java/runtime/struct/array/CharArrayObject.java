package runtime.struct.array;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import runtime.struct.ArrayObject;

@Getter
@Setter
public class CharArrayObject extends ArrayObject {
    private char[] array;

    public CharArrayObject(int len, String type) {
        super(len, type);
        array = new char[len];
    }
}