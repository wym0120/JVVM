package memory.jclass.runtimeConstantPool.constant.wrapper;

import lombok.Data;
import memory.jclass.runtimeConstantPool.constant.Constant;

@Data
public class LongWrapper implements Constant {
    private long value;

    public LongWrapper(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "long " + value;
    }
}
