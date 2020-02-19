package classloader;

import classloader.constantpool.ConstantPool;
import lombok.Data;

import java.nio.ByteBuffer;

/**
 * Description:
 * Required information to build an attribute
 * @author xxz
 * Created on 2020-02-18
 */
@Data
public class BuildInfo {
    private ConstantPool constantPool;
    private ByteBuffer byteBuffer;

    public BuildInfo(ByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer;
    }

    public BuildInfo(ConstantPool constantPool, ByteBuffer byteBuffer) {
        this.constantPool = constantPool;
        this.byteBuffer = byteBuffer;
    }

    public int getU1() {
        int signed = byteBuffer.get();
        return signed & 0xFF;
    }

    public int getU2() {
        int signed = byteBuffer.getShort();
        return signed & 0xFFFF;
    }

    public long getU4() {
        long signed = byteBuffer.getInt();
        return signed & 0xFFFFFFFFL;
    }
}
