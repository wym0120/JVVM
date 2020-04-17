package memory.jclass.runtimeConstantPool.constant.ref;

import classloader.ClassLoader;
import lombok.Data;
import memory.jclass.JClass;
import memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import memory.jclass.runtimeConstantPool.constant.Constant;

@Data
public abstract class SymRef implements Constant {
    public RuntimeConstantPool runtimeConstantPool;
    public String className;    //format : java/lang/Object
    public JClass clazz;

    public JClass getResolvedClass() throws ClassNotFoundException {
        if (clazz == null) {
            resolveClassRef();
        }
        return clazz;
    }

    public void resolveClassRef() throws ClassNotFoundException {
        JClass D = runtimeConstantPool.getClazz();
        JClass C = ClassLoader.getInstance().loadClass(className, D.getLoadEntryType());
        if (!C.isAccessibleTo(D)) {
            try {
                throw new IllegalAccessException(className + " is not accessible to " + D.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        clazz = C;
    }
}
