package memory.runtimeConstantPool.constant.wrapper;

import lombok.Data;
import memory.runtimeConstantPool.constant.Constant;

@Data
public class IntWrapper implements Constant {
    private int value;

    public IntWrapper(int value){
        this.value = value;
    }
}
