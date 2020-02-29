package memory.jclass.runtimeConstantPool.constant.ref;

import classloader.classfileparser.constantpool.info.MethodrefInfo;
import lombok.Data;
import memory.jclass.Method;
import memory.jclass.runtimeConstantPool.RuntimeConstantPool;

@Data
public class MethodRef extends MemberRef {
    private Method method;

    public MethodRef(RuntimeConstantPool runtimeConstantPool, MethodrefInfo methodrefInfo) {
        super(runtimeConstantPool, methodrefInfo);
    }
}
