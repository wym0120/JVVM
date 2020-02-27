package classloader.constantpool.info;

import java.nio.ByteBuffer;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class FloatInfo extends ConstantPoolInfo {
    private byte[] bytes;

    public FloatInfo(byte[] bytes) {
        this.bytes = bytes;
        if (bytes.length != 4) {
            throw new UnsupportedOperationException(
                    "Float constantpool info expects 4 bytes, actual is " + bytes.length);
        }
        super.tag = ConstantPoolInfo.FLOAT;

    }

    public Float getValue() {
        return ByteBuffer.wrap(bytes).getFloat();
    }
}
