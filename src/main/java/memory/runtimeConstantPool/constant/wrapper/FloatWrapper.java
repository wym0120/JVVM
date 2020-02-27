package memory.runtimeConstantPool.constant.wrapper;

import lombok.Data;
import memory.runtimeConstantPool.constant.Constant;

@Data
public class FloatWrapper implements Constant {
    private float value;

    public FloatWrapper(float value){
        this.value = value;
    }
}
