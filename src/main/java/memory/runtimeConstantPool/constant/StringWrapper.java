package memory.runtimeConstantPool.constant;

import lombok.Data;

@Data
public class StringWrapper implements Constant{
    private String value;

    public StringWrapper(String value){
        this.value = value;
    }
}
