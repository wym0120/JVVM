package runtime.struct.array;

import lombok.Data;
import runtime.struct.ArrayObject;
import runtime.struct.JObject;

@Data
public class RefArrayObject extends ArrayObject {
    private JObject[] array;

    public RefArrayObject(int len){
        super(len);
        array = new JObject[len];
    }
}
