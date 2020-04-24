package runtime.struct;

import lombok.Data;

@Data
public class ArrayObject extends JObject {
    protected int len;

    public ArrayObject(int len){
        if(len<0) throw new NegativeArraySizeException();
        this.len = len;
    }
}
