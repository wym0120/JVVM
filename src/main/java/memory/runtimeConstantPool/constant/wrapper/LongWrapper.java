package memory.runtimeConstantPool.constant.wrapper;

import lombok.Data;
import memory.runtimeConstantPool.constant.Constant;

@Data
public class LongWrapper implements Constant {
    private long value;

    public LongWrapper(long value){
        this.value = value;
    }
}
