package classloader.constantpool.info;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class InvokeDynamicInfo extends ConstantPoolInfo {
    private int bootstrapMethodAttrIndex;
    private int nameAndTypeIndex;

    public InvokeDynamicInfo(int bootstrapMethodAttrIndex, int nameAndTypeIndex) {
        this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
        super.tag = ConstantPoolInfo.INVOKE_DYNAMIC;
    }
}
