package memory.runtimeConstantPool.constant.ref;

import classloader.constantpool.info.MethodrefInfo;
import lombok.Data;
import memory.jclass.Method;
import memory.runtimeConstantPool.RuntimeConstantPool;

@Data
public class MethodRef extends MemberRef {
    private Method method;

    public MethodRef(RuntimeConstantPool runtimeConstantPool, MethodrefInfo methodrefInfo) {
        super(runtimeConstantPool, methodrefInfo);
    }
}
