package runtime.struct.array;

import lombok.Data;
import runtime.struct.ArrayObject;
import runtime.struct.JObject;

import java.util.Arrays;

@Data
public class RefArrayObject extends ArrayObject {
    private JObject[] array;

    public RefArrayObject(int len, String type) {
        super(len, type);
        array = new JObject[len];

    }
}
