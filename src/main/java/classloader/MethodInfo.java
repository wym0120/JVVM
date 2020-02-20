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
public class MethodInfo {
    private int accessFlags;
    private int nameIndex;
    private int descriptorIndex;
    private int attributesCount;
    private AttributeInfo[] attributes;

    public MethodInfo(Supplier<AttributeInfo> attributeBuilder, ByteBuffer in) {
        BuildUtil info = new BuildUtil(in);
        accessFlags = info.getU2();
        nameIndex = info.getU2();
        descriptorIndex = info.getU2();
        attributesCount = info.getU2();
        attributes = new AttributeInfo[attributesCount];
        for (int i = 0; i < attributes.length; i++) {
            attributes[i] = attributeBuilder.get();
        }


    }
}
