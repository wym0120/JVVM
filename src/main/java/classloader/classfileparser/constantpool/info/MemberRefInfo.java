package classloader.classfileparser.constantpool.info;

import classloader.classfileparser.constantpool.ConstantPool;
import com.sun.tools.javac.util.Pair;

public abstract class MemberRefInfo extends ConstantPoolInfo {

    public MemberRefInfo(ConstantPool myCP) {
        super(myCP);
    }


    public abstract String getClassName();

    protected String getClassName(int idx) {
        return ((ClassInfo) myCP.get(idx)).getClassName();
    }


    public abstract Pair<String, String> getNameAndDescriptor();
}
