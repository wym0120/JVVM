package classloader.classfileparser.constantpool.info;

import classloader.classfileparser.constantpool.ConstantPool;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class StringInfo extends ConstantPoolInfo{
    private int stringIndex;

    public StringInfo(ConstantPool constantPool, int stringIndex) {
        super(constantPool);
        this.stringIndex = stringIndex;
        super.tag = ConstantPoolInfo.STRING;
    }
}
