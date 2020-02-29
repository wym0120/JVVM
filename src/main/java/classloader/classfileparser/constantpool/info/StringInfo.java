package classloader.classfileparser.constantpool.info;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class StringInfo extends ConstantPoolInfo{
    private int stringIndex;

    public StringInfo(int stringIndex) {
        this.stringIndex = stringIndex;
        super.tag = ConstantPoolInfo.STRING;
    }
}
