package memory.jclass.runtimeConstantPool.constant.wrapper;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import memory.jclass.runtimeConstantPool.constant.Constant;

@Getter
@Setter
public class FloatWrapper implements Constant {
    private float value;

    public FloatWrapper(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "float " + value;
    }
}
