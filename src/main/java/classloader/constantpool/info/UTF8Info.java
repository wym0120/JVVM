package classloader.constantpool.info;

import com.sun.tools.javac.util.Pair;

import java.nio.ByteBuffer;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class UTF8Info extends ConstantPoolInfo {
    private short length;
    private byte[] bytes;

    public UTF8Info(short length, byte[] bytes) {
        this.length = length;
        this.bytes = bytes;
        if (bytes.length != length) {
            throw new UnsupportedOperationException(
                    "UTF8 constantpool info expects " + length + " but actual is " + bytes.length);
        }
        super.tag = ConstantPoolInfo.UTF8;
    }

    static Pair<UTF8Info, Integer> getInstance(byte[] in, int offset) {
        ByteBuffer buffer = ByteBuffer.wrap(in, offset, in.length - offset);
        short length = buffer.getShort();
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = buffer.get();
        }
        return Pair.of(new UTF8Info(length, bytes), 2 + length);
    }
}
