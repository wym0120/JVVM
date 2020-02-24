package memory.runtimeConstantPool.constant;

import lombok.Data;

@Data
public class IntWrapper implements Constant {
    private int value;

    public IntWrapper(int value){
        this.value = value;
    }
}
