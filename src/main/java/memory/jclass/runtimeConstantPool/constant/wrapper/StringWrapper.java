package memory.jclass.runtimeConstantPool.constant.wrapper;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import memory.jclass.runtimeConstantPool.constant.Constant;

//not support String class in current version
@Getter
@Setter
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
