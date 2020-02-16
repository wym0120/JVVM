package classloader.constantpool.info;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class ClassInfo extends ConstantPoolInfo {
    private short nameIndex;

    public ClassInfo(short nameIndex) {
        this.nameIndex = nameIndex;
        super.tag = ConstantPoolInfo.CLASS;
    }
}
