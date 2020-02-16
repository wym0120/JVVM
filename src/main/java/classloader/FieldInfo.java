package classloader;

import classloader.attribute.AttributeInfo;

import java.nio.ByteBuffer;
import java.util.function.Supplier;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-10
 */
public class FieldInfo {
    private short accessFlags;
    private short nameIndex;
    private short descriptorIndex;
    private short attributesCount;
    private AttributeInfo[] attributes;

    public FieldInfo(Supplier<AttributeInfo> attributeBuilder, ByteBuffer in) {
        this.accessFlags = in.getShort();
        this.nameIndex = in.getShort();
        this.descriptorIndex = in.getShort();
        this.attributesCount = in.getShort();
        this.attributes = new AttributeInfo[this.attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            this.attributes[i] = attributeBuilder.get();
        }
    }
}
