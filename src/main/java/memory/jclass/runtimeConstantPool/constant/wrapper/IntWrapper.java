package memory.jclass.runtimeConstantPool.constant.wrapper;

import lombok.Data;
import memory.jclass.runtimeConstantPool.constant.Constant;

@Data
public class IntWrapper implements Constant {
    private int value;

    public IntWrapper(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "int " + value;
    }
}
