package memory.runtimeConstantPool.constant.ref;

import classloader.constantpool.info.FieldrefInfo;
import lombok.Data;
import memory.jclass.Field;
import memory.runtimeConstantPool.RuntimeConstantPool;

@Data
public class FieldRef extends MemberRef {
    private Field field;

    public FieldRef(RuntimeConstantPool runtimeConstantPool, FieldrefInfo fieldrefInfo) {
        super(runtimeConstantPool, fieldrefInfo);
    }
}
