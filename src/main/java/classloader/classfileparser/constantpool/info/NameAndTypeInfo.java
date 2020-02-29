package classloader.classfileparser.constantpool.info;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class NameAndTypeInfo extends ConstantPoolInfo {
    private int nameIndex;
    private int descriptorIndex;

    public NameAndTypeInfo(int nameIndex, int descriptorIndex) {
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        super.tag = ConstantPoolInfo.NAME_AND_TYPE;
    }
}
