package memory.jclass.runtimeConstantPool.constant.wrapper;

import lombok.Data;
import memory.jclass.runtimeConstantPool.constant.Constant;

@Data
public class FloatWrapper implements Constant {
    private float value;

    public FloatWrapper(float value){
        this.value = value;
    }
}
