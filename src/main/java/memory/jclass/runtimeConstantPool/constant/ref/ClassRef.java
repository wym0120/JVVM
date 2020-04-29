package memory.jclass.runtimeConstantPool.constant.ref;

import classloader.classfileparser.constantpool.info.ClassInfo;
import memory.jclass.runtimeConstantPool.RuntimeConstantPool;

public class ClassRef extends SymRef {
    public ClassRef(RuntimeConstantPool runtimeConstantPool, ClassInfo classInfo) {
        this.runtimeConstantPool = runtimeConstantPool;
        this.className = classInfo.getClassName();
    }

    @Override
    public String toString() {
        return "ClassRef to " + className;
    }

}
