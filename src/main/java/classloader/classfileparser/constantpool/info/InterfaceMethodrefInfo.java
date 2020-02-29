package classloader.classfileparser.constantpool.info;

import com.sun.tools.javac.util.Pair;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class InterfaceMethodrefInfo extends MemberRefInfo {
    private int classIndex;
    private int nameAndTypeIndex;

    public InterfaceMethodrefInfo(int classIndex, int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
        super.tag = ConstantPoolInfo.INTERFACE_METHOD_REF;
    }

    //todo
    public String getClassName() {
        return null;
    }

    //todo
    public Pair<String, String> getNameAndDescriptor() {
        return null;
    }
}
