package classloader.classfileparser.constantpool.info;

import classloader.classfileparser.constantpool.ConstantPool;
import org.apache.commons.lang3.ArrayUtils;

import java.nio.ByteBuffer;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class LongInfo extends ConstantPoolInfo {
    private byte[] highBytes;
    private byte[] lowBytes;
    private long value;

    public LongInfo(ConstantPool constantPool, byte[] highBytes, byte[] lowBytes) {
        super(constantPool);
        this.highBytes = highBytes;
        this.lowBytes = lowBytes;
        if (lowBytes.length != 4 || highBytes.length != 4) {
            throw new UnsupportedOperationException(
                    "Long constantpool info expects 8 bytes, actual is " + lowBytes.length + " " + highBytes.length);
        }
        this.value = ByteBuffer
                .wrap(ArrayUtils.addAll(highBytes, lowBytes))
                .getLong();
        super.tag = ConstantPoolInfo.LONG;

    }

    @Override
    public int getEntryLength() {
        return 2;
    }


    public Long getValue() {
        return value;
    }
}
