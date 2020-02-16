package classloader.attribute;

import classloader.attribute.AttributeInfo;

import java.nio.ByteBuffer;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class ConstantValueAttr extends AttributeInfo{
    private short constantValueIndex;

    public ConstantValueAttr(ByteBuffer byteBuffer, short index, int length) {
        super(index, length);
        constantValueIndex = byteBuffer.getShort();
    }
}
