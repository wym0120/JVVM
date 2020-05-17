package memory.jclass.runtimeConstantPool.constant.ref;

import classloader.classfileparser.constantpool.info.MemberRefInfo;
import lombok.Getter;
import lombok.Setter;
import memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import org.apache.commons.lang3.tuple.Pair;

@Getter
@Setter
public abstract class MemberRef extends SymRef {
    protected String name;
    protected String descriptor;

    public MemberRef(RuntimeConstantPool runtimeConstantPool, MemberRefInfo info) {
        this.runtimeConstantPool = runtimeConstantPool;
        this.className = info.getClassName();
        Pair<String, String> nameAndType = info.getNameAndDescriptor();
        this.name = nameAndType.getKey();
        this.descriptor = nameAndType.getValue();
    }

}
