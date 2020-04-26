package runtime.struct;

import lombok.Data;

@Data
public class ArrayObject extends JObject {
    protected int len;
    protected String type;

    public ArrayObject(int len, String type) {
        if (len < 0) throw new NegativeArraySizeException();
        this.len = len;
        this.type = type;
    }
}
