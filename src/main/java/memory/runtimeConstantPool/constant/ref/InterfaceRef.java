package memory.runtimeConstantPool.constant.ref;

import classloader.constantpool.info.InterfaceMethodrefInfo;
import lombok.Data;
import memory.jclass.Method;
import memory.runtimeConstantPool.RuntimeConstantPool;

@Data
public class InterfaceRef extends MemberRef {
    private Method method;

    public InterfaceRef(RuntimeConstantPool runtimeConstantPool, InterfaceMethodrefInfo interfaceMethodrefInfo) {
        super(runtimeConstantPool, interfaceMethodrefInfo);
        //method
    }
}