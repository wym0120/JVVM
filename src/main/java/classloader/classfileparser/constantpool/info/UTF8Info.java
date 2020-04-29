package classloader.classfileparser.constantpool.info;

import classloader.classfileparser.constantpool.ConstantPool;
import com.sun.tools.javac.util.Pair;
import lombok.Getter;

import java.nio.ByteBuffer;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
@Getter
public class UTF8Info extends ConstantPoolInfo {
    private int length;
    private byte[] bytes;
    private String myString;

    public UTF8Info(ConstantPool constantPool, int length, byte[] bytes) {
        super(constantPool);
        this.length = length;
        this.bytes = bytes;
        if (bytes.length != length) {
            throw new UnsupportedOperationException(
                    "UTF8 constantpool info expects " + length + " but actual is " + bytes.length);
        }
        myString = new String(bytes);
        super.tag = ConstantPoolInfo.UTF8;
    }

    static Pair<UTF8Info, Integer> getInstance(ConstantPool constantPool, byte[] in, int offset) {
        ByteBuffer buffer = ByteBuffer.wrap(in, offset, in.length - offset);
        int length = 0xFFFF & (int) buffer.getShort();
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = buffer.get();
        }
        return Pair.of(new UTF8Info(constantPool, length, bytes), 2 + length);
    }

    public String getString() {
        return myString;
    }
}
