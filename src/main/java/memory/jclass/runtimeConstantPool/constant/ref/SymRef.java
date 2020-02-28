package memory.jclass.runtimeConstantPool.constant.ref;

import lombok.Data;
import memory.jclass.JClass;
import memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import memory.jclass.runtimeConstantPool.constant.Constant;

@Data
public abstract class SymRef implements Constant {
    public RuntimeConstantPool runtimeConstantPool;
    //format : java/lang/Object
    public String className;
    public JClass clazz;
}
