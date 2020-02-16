package classloader.constantpool.info;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class MethodTypeInfo extends ConstantPoolInfo {
    private short descriptorIndex;

    public MethodTypeInfo(short descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
        super.tag = ConstantPoolInfo.METHOD_TYPE;
    }
}
