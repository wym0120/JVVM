package memory.jclass.runtimeConstantPool.constant.wrapper;

import lombok.Data;
import memory.jclass.runtimeConstantPool.constant.Constant;

//not support String class in current version
@Data
public class StringWrapper implements Constant {
    private String value;

    public StringWrapper(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "String " + value;
    }
}
