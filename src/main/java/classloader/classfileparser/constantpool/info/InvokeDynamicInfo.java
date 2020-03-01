package classloader.classfileparser.constantpool.info;

import classloader.classfileparser.constantpool.ConstantPool;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class InvokeDynamicInfo extends ConstantPoolInfo {
    private int bootstrapMethodAttrIndex;
    private int nameAndTypeIndex;

    public InvokeDynamicInfo(ConstantPool constantPool, int bootstrapMethodAttrIndex, int nameAndTypeIndex) {
        super(constantPool);
        this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
        super.tag = ConstantPoolInfo.INVOKE_DYNAMIC;
    }
}
