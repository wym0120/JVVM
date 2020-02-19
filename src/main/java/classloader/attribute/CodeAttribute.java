package classloader.attribute;

import classloader.BuildInfo;
import lombok.Data;

import java.nio.ByteBuffer;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-18
 */
@Data
public class CodeAttribute extends AttributeInfo {
    private int maxStack;
    private int  maxLocal;
    private int codeLength;
    private byte[] code;
    private int exceptionTableLength;
    private ExceptionTable[] exceptionTable;
    private int attributesCount;
    private AttributeInfo[] attributes;

    public CodeAttribute(BuildInfo buildInfo, int index, int length) {
        super(index, length);
        maxStack = buildInfo.getU2();
        maxLocal = buildInfo.getU2();
        codeLength = (int) buildInfo.getU4();
        code = new byte[codeLength];
        ByteBuffer buffer = buildInfo.getByteBuffer();
        for (int i = 0; i < codeLength; i++) {
            code[i] = buffer.get();
        }
        exceptionTableLength = buildInfo.getU2();
        exceptionTable = new ExceptionTable[exceptionTableLength];
        for (int i = 0; i < exceptionTableLength; i++) {
            exceptionTable[i] = new ExceptionTable(buildInfo.getU2(), buildInfo.getU2(),
                    buildInfo.getU2(), buildInfo.getU2());
        }
        attributesCount = buildInfo.getU2();
        attributes = new AttributeInfo[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = AttributeBuilder.createAttribute(buildInfo);
        }
    }
}

