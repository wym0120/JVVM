package memory.runtimeConstantPool.constant;

import lombok.Data;

@Data
public class FloatWrapper implements Constant {
    private float value;

    public FloatWrapper(float value){
        this.value = value;
    }
}
