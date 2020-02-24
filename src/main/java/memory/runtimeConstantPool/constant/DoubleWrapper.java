package memory.runtimeConstantPool.constant;

import lombok.Data;

@Data
public class DoubleWrapper implements Constant {
    private double value;

    public DoubleWrapper(double value){
        this.value = value;
    }
}
