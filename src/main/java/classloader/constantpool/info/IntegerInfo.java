package classloader.constantpool.info;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class IntegerInfo extends ConstantPoolInfo {
    private byte[] bytes;

    public IntegerInfo(byte[] bytes) {
        this.bytes = bytes;
        if (bytes.length != 4) {
            throw new UnsupportedOperationException(
                    "Integer constantpool info expects 4 bytes, actual is " + bytes.length);
        }
        super.tag = ConstantPoolInfo.INTEGER;
    }
}
