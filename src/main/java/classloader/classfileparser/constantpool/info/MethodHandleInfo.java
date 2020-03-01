package classloader.classfileparser.constantpool.info;

import classloader.classfileparser.constantpool.ConstantPool;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class MethodHandleInfo extends ConstantPoolInfo{
    private int referenceKind;
    private int referenceIndex;

    public MethodHandleInfo(ConstantPool constantPool, int referenceKind, int referenceIndex) {
        super(constantPool);
        this.referenceKind = referenceKind;
        this.referenceIndex = referenceIndex;
        super.tag = ConstantPoolInfo.METHOD_HANDLE;
    }
}
