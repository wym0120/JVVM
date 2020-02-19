package classloader.constantpool.info;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class MethodrefInfo extends ConstantPoolInfo {
    private int classIndex;
    private int nameAndTypeIndex;

    public MethodrefInfo(int classIndex, int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
        super.tag = ConstantPoolInfo.METHOD_REF;
    }
}
