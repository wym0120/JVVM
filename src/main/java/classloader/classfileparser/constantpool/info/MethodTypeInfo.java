package classloader.classfileparser.constantpool.info;

import classloader.classfileparser.constantpool.ConstantPool;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class MethodTypeInfo extends ConstantPoolInfo {
    private int descriptorIndex;

    public MethodTypeInfo(ConstantPool constantPool, int descriptorIndex) {
        super(constantPool);
        this.descriptorIndex = descriptorIndex;
        super.tag = ConstantPoolInfo.METHOD_TYPE;
    }
}
