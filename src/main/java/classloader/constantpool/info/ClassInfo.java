package classloader.constantpool.info;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class ClassInfo extends ConstantPoolInfo {
    private int nameIndex;

    public ClassInfo(int nameIndex) {
        this.nameIndex = nameIndex;
        super.tag = ConstantPoolInfo.CLASS;
    }

    //todo:
    public String getClassName() {
        return null;
    }
}
