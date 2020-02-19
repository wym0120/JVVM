package classloader.constantpool.info;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class MethodTypeInfo extends ConstantPoolInfo {
    private int descriptorIndex;

    public MethodTypeInfo(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
        super.tag = ConstantPoolInfo.METHOD_TYPE;
    }
}
