package memory.jclass.runtimeConstantPool.constant.wrapper;

import lombok.Getter;
import lombok.Setter;
import memory.jclass.runtimeConstantPool.constant.Constant;

@Getter
@Setter
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
