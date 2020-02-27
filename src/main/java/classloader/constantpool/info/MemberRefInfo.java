package classloader.constantpool.info;

import com.sun.tools.javac.util.Pair;

public abstract class MemberRefInfo extends ConstantPoolInfo {
    //todo
    public abstract String getClassName();

    //todo
    public abstract Pair<String, String> getNameAndDescriptor();
}
