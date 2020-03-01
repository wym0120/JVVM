package classloader.classfileparser.constantpool.info;

import classloader.classfileparser.constantpool.ConstantPool;

import java.nio.ByteBuffer;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class IntegerInfo extends ConstantPoolInfo {
    private byte[] bytes;
    private int value;

    public IntegerInfo(ConstantPool constantPool, byte[] bytes) {
        super(constantPool);
        this.bytes = bytes;
        if (bytes.length != 4) {
            throw new UnsupportedOperationException(
                    "Integer constantpool info expects 4 bytes, actual is " + bytes.length);
        }
        this.value = ByteBuffer.wrap(bytes).getInt();
        super.tag = ConstantPoolInfo.INTEGER;
    }


    public Integer getValue() {
        return value;
    }
}
