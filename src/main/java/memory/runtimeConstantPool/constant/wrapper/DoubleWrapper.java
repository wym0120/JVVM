package memory.runtimeConstantPool.constant.wrapper;

import lombok.Data;
import memory.runtimeConstantPool.constant.Constant;

@Data
public class DoubleWrapper implements Constant {
    private double value;

    public DoubleWrapper(double value){
        this.value = value;
    }
}
