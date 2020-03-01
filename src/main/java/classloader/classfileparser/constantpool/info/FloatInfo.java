package classloader.classfileparser.constantpool.info;

import classloader.classfileparser.constantpool.ConstantPool;

import java.nio.ByteBuffer;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class FloatInfo extends ConstantPoolInfo {
    private byte[] bytes;
    private float value;

    public FloatInfo(ConstantPool constantPool, byte[] bytes) {
        super(constantPool);
        this.bytes = bytes;
        if (bytes.length != 4) {
            throw new UnsupportedOperationException(
                    "Float constantpool info expects 4 bytes, actual is " + bytes.length);
        }
        this.value = ByteBuffer.wrap(bytes).getFloat();
        super.tag = ConstantPoolInfo.FLOAT;

    }

    public Float getValue() {
        return value;
    }
}
