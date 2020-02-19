package classloader.constantpool.info;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class FieldrefInfo extends ConstantPoolInfo {
    private int classIndex;
    private int nameAndTypeIndex;

    public FieldrefInfo(int classIndex, int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
        super.tag = ConstantPoolInfo.FIELD_REF;
    }
}
