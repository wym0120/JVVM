package classloader.classfileparser;

import classloader.classfileparser.attribute.AttributeInfo;
import classloader.classfileparser.attribute.CodeAttribute;
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
public class MethodInfo {
    private int accessFlags;
    private int nameIndex;
    private String name;
    private int descriptorIndex;
    private String descriptor;
    private int attributesCount;
    private AttributeInfo[] attributes;
    private CodeAttribute code;


    public MethodInfo(ConstantPool constantPool, Supplier<AttributeInfo> attributeBuilder, ByteBuffer in) {
        BuildUtil info = new BuildUtil(in);
        accessFlags = info.getU2();
        nameIndex = info.getU2();
        descriptorIndex = info.getU2();
        attributesCount = info.getU2();
        attributes = new AttributeInfo[attributesCount];
        for (int i = 0; i < attributes.length; i++) {
            attributes[i] = attributeBuilder.get();
        }
        this.name = ((UTF8Info) constantPool.get(this.nameIndex)).getString();
        this.descriptor = ((UTF8Info) constantPool.get(this.descriptorIndex)).getString();
    }


    public short getAccessFlags() {
        return (short) (accessFlags & 0xFFFF);
    }


    public String getName() {
        return name;
    }


    public String getDescriptor() {
        return descriptor;
    }


    public CodeAttribute getCodeAttribute() {
        if (code == null) {
            for (AttributeInfo attribute : attributes) {
                if (attribute instanceof CodeAttribute) {
                    code = (CodeAttribute) attribute;
                    return code;
                }
            }
//            throw new UnsupportedOperationException("No code attribute!");
        }
        return code;

    }
}
