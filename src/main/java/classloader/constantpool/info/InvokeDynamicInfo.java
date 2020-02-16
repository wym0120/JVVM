package classloader.constantpool.info;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class InvokeDynamicInfo extends ConstantPoolInfo {
    private short bootstrapMethodAttrIndex;
    private short nameAndTypeIndex;

    public InvokeDynamicInfo(short bootstrapMethodAttrIndex, short nameAndTypeIndex) {
        this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
        super.tag = ConstantPoolInfo.INVOKE_DYNAMIC;
    }
}
