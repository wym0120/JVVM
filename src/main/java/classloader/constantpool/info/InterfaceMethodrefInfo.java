package classloader.constantpool.info;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class InterfaceMethodrefInfo extends ConstantPoolInfo {
    private short classIndex;
    private short nameAndTypeIndex;

    public InterfaceMethodrefInfo(short classIndex, short nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
        super.tag = ConstantPoolInfo.INTERFACE_METHOD_REF;
    }
}
