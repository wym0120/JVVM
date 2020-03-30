package runtime.struct.array;

import lombok.Data;
import runtime.struct.ArrayObject;

@Data
public class CharArrayObject extends ArrayObject {
    private char[] array;

    public CharArrayObject(int len){
        super(len);
        array = new char[len];
    }
}