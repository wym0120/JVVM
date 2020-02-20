package classloader.attribute;

import classloader.BuildUtil;
import lombok.Data;

import java.nio.ByteBuffer;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-10
 */
@Data
public class AttributeInfo {

    private int attributeNameAndIndex;
    private int attributeLength;
    private byte[] info;

    protected AttributeInfo(int attributeNameAndIndex, int attributeLength) {
        this.attributeNameAndIndex = attributeNameAndIndex;
        this.attributeLength = attributeLength;
    }

    public AttributeInfo(BuildUtil buildUtil, int index, int length) {
        ByteBuffer byteBuffer = buildUtil.getByteBuffer();
        this.attributeNameAndIndex = index;
        this.attributeLength = length;
        this.info = new byte[this.attributeLength];
        for (int i = 0; i < this.attributeLength; i++) {
            info[i] = byteBuffer.get();
        }
    }

//
//    protected AttributeInfo(byte[] in, int offset) {
//        ByteBuffer buffer = ByteBuffer.wrap(in, offset, in.length - offset);
//        this.attributeNameAndIndex = buffer.getShort();
//        this.attributeLength = buffer.getInt();
//        this.info = new byte[this.attributeLength];
//        for (int i = 0; i < this.attributeLength; i++) {
//            info[i] = buffer.get();
//        }
//    }

}
