package classloader.constantpool.info;

import com.sun.tools.classfile.ConstantPool;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class NameAndTypeInfo extends ConstantPoolInfo {
    private short nameIndex;
    private short descriptorIndex;

    public NameAndTypeInfo(short nameIndex, short descriptorIndex) {
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        super.tag = ConstantPoolInfo.NAME_AND_TYPE;
    }
}
