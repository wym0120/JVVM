package classloader.constantpool.info;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class MethodrefInfo extends ConstantPoolInfo {
    private short classIndex;
    private short nameAndTypeIndex;

    public MethodrefInfo(short classIndex, short nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
        super.tag = ConstantPoolInfo.METHOD_REF;
    }
}
