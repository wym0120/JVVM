package classloader.classfileparser.constantpool.info;

import classloader.classfileparser.constantpool.ConstantPool;
import com.sun.tools.javac.util.Pair;

import java.util.Optional;

public abstract class MemberRefInfo extends ConstantPoolInfo {

    public MemberRefInfo(ConstantPool myCP) {
        super(myCP);
    }


    public abstract String getClassName();

    protected String getClassName(int idx) {
        return ((UTF8Info) myCP.get(idx)).getString();
    }


    public abstract Pair<String, String> getNameAndDescriptor();
}
