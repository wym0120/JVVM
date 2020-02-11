package classloader;

import lombok.Data;

import java.nio.ByteBuffer;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-10
 */
@Data
public class ClassFile {
    private int magic;
    private short minorVersion;
    private short majorVersion;
    private short constantPoolCount;
    private ConstantPoolInfo[] constantPool;
    private short accessFlags;
    private short thisClass;
    private short superClass;
    private short interfacesCount;
    private short[] interfaces;
    private short fieldsCount;
    private FieldInfo[] fields;
    private short methodsCount;
    private MethodInfo[] methods;
    private short attributeCount;
    private AttributeInfo[] attributes;

    public ClassFile(byte[] classfile) {
        ByteBuffer in = ByteBuffer.wrap(classfile);
        this.magic = in.getInt();
        assert this.magic == 0xCAFEBABE;
        this.minorVersion = in.getShort();
        this.majorVersion = in.getShort();
        this.constantPoolCount = in.getShort();
        int currentPosition = in.position();

        System.out.println();


    }
}
