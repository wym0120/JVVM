package memory.runtimeConstantPool.constant;

import lombok.Data;
import memory.jclass.Field;

@Data
public class FieldRef extends SymRef {
    private Field field;
    private String name;
    private String descriptor;
}
