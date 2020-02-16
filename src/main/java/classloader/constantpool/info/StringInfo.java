package classloader.constantpool.info;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class StringInfo extends ConstantPoolInfo{
    private short stringIndex;

    public StringInfo(short stringIndex) {
        this.stringIndex = stringIndex;
        super.tag = ConstantPoolInfo.STRING;
    }
}
