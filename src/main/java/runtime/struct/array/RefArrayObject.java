package runtime.struct.array;

import lombok.Getter;
import lombok.Setter;
import runtime.struct.ArrayObject;
import runtime.struct.JObject;

@Getter
@Setter
public class RefArrayObject extends ArrayObject {
    private JObject[] array;

    public RefArrayObject(int len, String type) {
        super(len, type);
        array = new JObject[len];

    }
}
