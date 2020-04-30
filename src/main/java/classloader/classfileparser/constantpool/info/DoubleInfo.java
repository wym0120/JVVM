package classloader.classfileparser.constantpool.info;

import classloader.classfileparser.constantpool.ConstantPool;

import java.nio.ByteBuffer;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class DoubleInfo extends ConstantPoolInfo {
    private byte[] highBytes;
    private byte[] lowBytes;
    private double value;

    public DoubleInfo(ConstantPool constantPool, byte[] highBytes, byte[] lowBytes) {
        super(constantPool);
        this.highBytes = highBytes;
        this.lowBytes = lowBytes;
        if (lowBytes.length != 4 || highBytes.length != 4) {
            throw new UnsupportedOperationException(
                    "Double constantpool info expects 8 bytes, actual is " + lowBytes.length + " " + highBytes.length);
        }

        byte[] valueBytes = new byte[8];
        System.arraycopy(highBytes, 0, valueBytes, 0, 4);
        System.arraycopy(lowBytes, 0, valueBytes, 4, 4);
        this.value = ByteBuffer.wrap(valueBytes).getDouble();
        super.tag = ConstantPoolInfo.DOUBLE;

    }

    @Override
    public int getEntryLength() {
        return 2;
    }


    public Double getValue() {
        return value;
    }
}
