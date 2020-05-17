package memory.jclass.runtimeConstantPool.constant.ref;

import classloader.classfileparser.constantpool.info.MemberRefInfo;
import com.sun.tools.javac.util.Pair;
import lombok.Getter;
import lombok.Setter;
import memory.jclass.runtimeConstantPool.RuntimeConstantPool;

@Getter
@Setter
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
