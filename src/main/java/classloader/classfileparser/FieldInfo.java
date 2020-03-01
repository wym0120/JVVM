package classloader.classfileparser;

import classloader.classfileparser.attribute.AttributeInfo;
import classloader.classfileparser.attribute.ConstantValueAttr;
import classloader.classfileparser.constantpool.ConstantPool;
import classloader.classfileparser.constantpool.info.UTF8Info;

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
    private String name;
    private short descriptorIndex;
    private String descriptor;
    private short attributesCount;
    private AttributeInfo[] attributes;

    public FieldInfo(ConstantPool constantPool, Supplier<AttributeInfo> attributeBuilder, ByteBuffer in) {
        this.accessFlags = in.getShort();
        this.nameIndex = in.getShort();
        this.descriptorIndex = in.getShort();
        this.attributesCount = in.getShort();
        this.attributes = new AttributeInfo[this.attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            this.attributes[i] = attributeBuilder.get();
        }
        this.name = ((UTF8Info) constantPool.get(this.nameIndex)).getString();
        this.descriptor = ((UTF8Info) constantPool.get(this.descriptorIndex)).getString();

    }


    public short getAccessFlags() {
        return accessFlags;
    }


    public String getName() {
        return name;
    }


    public String getDescriptor() {
        return descriptor;
    }

    //todo:
    //XXX: 这是个啥玩意儿？？
    public ConstantValueAttr getConstantValueAttr() {
        return null;
    }
}
