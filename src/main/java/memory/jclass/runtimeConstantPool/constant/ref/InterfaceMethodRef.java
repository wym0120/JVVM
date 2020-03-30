package memory.jclass.runtimeConstantPool.constant.ref;

import classloader.classfileparser.constantpool.info.InterfaceMethodrefInfo;
import lombok.Data;
import memory.jclass.Method;
import memory.jclass.runtimeConstantPool.RuntimeConstantPool;

@Data
public class InterfaceMethodRef extends MemberRef {
    private Method method;

    public InterfaceMethodRef(RuntimeConstantPool runtimeConstantPool, InterfaceMethodrefInfo interfaceMethodrefInfo) {
        super(runtimeConstantPool, interfaceMethodrefInfo);
        //method
    }
}
