package classloader;

import com.sun.tools.javac.util.Pair;

import java.nio.ByteBuffer;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-10
 */
public class ConstantPoolInfo {
    private static final byte CLASS = 7;
    private static final byte FIELD_REF = 9;
    private static final byte METHOD_REF = 10;
    private static final byte INTERFACE_METHOD_REF = 11;
    private static final byte STRING = 8;
    private static final byte INTEGER = 3;
    private static final byte FLOAT = 4;
    private static final byte LONG = 5;
    private static final byte DOUBLE = 6;
    private static final byte NAME_AND_TYPE = 12;
    private static final byte UTF8 = 1;
    private static final byte METHOD_HANDLE = 15;
    private static final byte METHOD_TYPE = 16;
    private static final byte INVOKE_DYNAMIC = 18;
    protected byte tag;

    /**
     * return the constant pool info instance and
     * the number of bytes read by this method
     */
    public static Pair<ConstantPoolInfo, Integer> getConstantPoolInfo(byte[] classfile, int ofs) {
        ByteBuffer in = ByteBuffer.wrap(classfile, ofs, classfile.length - ofs);
        ConstantPoolInfo ret;
        byte tag = in.get();
        switch (tag) {
            case CLASS:

                break;
            case FIELD_REF:

            case METHOD_REF:

            case INTERFACE_METHOD_REF:

            case STRING:

            case INTEGER:

            case FLOAT:

            case LONG:

            case DOUBLE:

            case NAME_AND_TYPE:

            case UTF8:

            case METHOD_HANDLE:

            case METHOD_TYPE:

            case INVOKE_DYNAMIC:

            default:
                throw new UnsupportedOperationException("Unsupported constant pool tag" + tag);
        }
        return new Pair<>(null, 0);
    }
}
