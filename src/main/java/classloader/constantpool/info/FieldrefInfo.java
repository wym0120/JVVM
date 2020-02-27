package classloader.constantpool.info;

import com.sun.tools.javac.util.Pair;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class FieldrefInfo extends MemberRefInfo {
    private int classIndex;
    private int nameAndTypeIndex;

    public FieldrefInfo(int classIndex, int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
        super.tag = ConstantPoolInfo.FIELD_REF;
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
