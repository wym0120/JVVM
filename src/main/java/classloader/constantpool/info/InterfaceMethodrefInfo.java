package classloader.constantpool.info;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class InterfaceMethodrefInfo extends ConstantPoolInfo {
    private int classIndex;
    private int nameAndTypeIndex;

    public InterfaceMethodrefInfo(int classIndex, int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
        super.tag = ConstantPoolInfo.INTERFACE_METHOD_REF;
    }
}
