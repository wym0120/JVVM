package memory.runtimeConstantPool.constant.ref;

import classloader.constantpool.info.MemberRefInfo;
import com.sun.tools.javac.util.Pair;
import lombok.Data;
import memory.runtimeConstantPool.RuntimeConstantPool;

@Data
public abstract class MemberRef extends SymRef {
    protected String name;
    protected String descriptor;

    public MemberRef(RuntimeConstantPool runtimeConstantPool, MemberRefInfo info) {
        this.runtimeConstantPool = runtimeConstantPool;
        this.className = info.getClassName();
        Pair<String, String> nameAndType = info.getNameAndDescriptor();
        this.name = nameAndType.fst;
        this.descriptor = nameAndType.snd;
    }

}
