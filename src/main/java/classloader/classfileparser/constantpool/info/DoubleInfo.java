package classloader.classfileparser.constantpool.info;

import org.apache.commons.lang3.ArrayUtils;

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

    public DoubleInfo(byte[] highBytes, byte[] lowBytes) {
        this.highBytes = highBytes;
        this.lowBytes = lowBytes;
        if (lowBytes.length != 4 || highBytes.length != 4) {
            throw new UnsupportedOperationException(
                    "Double constantpool info expects 8 bytes, actual is " + lowBytes.length + " " + highBytes.length);
        }
        super.tag = ConstantPoolInfo.DOUBLE;

    }

    @Override
    public int getEntryLength() {
        return 2;
    }

    //todo:
    public Double getValue() {
        byte[] bytes = ArrayUtils.addAll(highBytes, lowBytes);
        return ByteBuffer.wrap(bytes).getDouble();
    }
}
