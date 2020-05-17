package com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.ref;

import com.njuse.seecjvm.classloader.ClassLoader;
import com.njuse.seecjvm.memory.jclass.JClass;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.Constant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
