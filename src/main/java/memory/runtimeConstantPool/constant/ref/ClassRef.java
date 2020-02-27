package memory.runtimeConstantPool.constant.ref;

import classloader.constantpool.info.ClassInfo;
import memory.runtimeConstantPool.RuntimeConstantPool;

public class ClassRef extends SymRef {
    public ClassRef(RuntimeConstantPool runtimeConstantPool, ClassInfo classInfo) {
        this.runtimeConstantPool = runtimeConstantPool;
        this.className = classInfo.getClassName();
    }

}
