package memory.runtimeConstantPool.constant;

import lombok.Data;
import memory.jclass.JClass;
import memory.runtimeConstantPool.RuntimeConstantPool;

@Data
public class SymRef {
    public RuntimeConstantPool runtimeConstantPool;
    public String className;
    public JClass clazz;
}
