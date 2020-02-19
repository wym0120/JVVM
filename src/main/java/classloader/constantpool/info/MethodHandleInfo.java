package classloader.constantpool.info;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class MethodHandleInfo extends ConstantPoolInfo{
    private int referenceKind;
    private int referenceIndex;

    public MethodHandleInfo(int referenceKind, int referenceIndex) {
        this.referenceKind = referenceKind;
        this.referenceIndex = referenceIndex;
        super.tag = ConstantPoolInfo.METHOD_HANDLE;
    }
}
