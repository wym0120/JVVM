package memory.jclass.runtimeConstantPool.constant.wrapper;

import lombok.Getter;
import lombok.Setter;
import memory.jclass.runtimeConstantPool.constant.Constant;

@Getter
@Setter
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
