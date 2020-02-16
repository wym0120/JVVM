package classloader.constantpool.info;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class MethodHandleInfo extends ConstantPoolInfo{
    private byte referenceKind;
    private short referenceIndex;

    public MethodHandleInfo(byte referenceKind, short referenceIndex) {
        this.referenceKind = referenceKind;
        this.referenceIndex = referenceIndex;
        super.tag = ConstantPoolInfo.METHOD_HANDLE;
    }
}
