package memory.runtimeConstantPool.constant;

import lombok.Data;

@Data
public class LongWrapper implements Constant {
    private long value;

    public LongWrapper(long value){
        this.value = value;
    }
}
