package memory.jclass.runtimeConstantPool.constant.wrapper;

import lombok.Data;
import memory.jclass.runtimeConstantPool.constant.Constant;

@Data
public class DoubleWrapper implements Constant {
    private double value;

    public DoubleWrapper(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "double " + value;
    }
}
