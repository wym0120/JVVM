package classloader.constantpool.info;

import com.sun.tools.javac.util.Pair;

import java.nio.ByteBuffer;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-10
 */
public class ConstantPoolInfo {
    protected static final byte CLASS = 7;
    protected static final byte FIELD_REF = 9;
    protected static final byte METHOD_REF = 10;
    protected static final byte INTERFACE_METHOD_REF = 11;
    protected static final byte STRING = 8;
    protected static final byte INTEGER = 3;
    protected static final byte FLOAT = 4;
    protected static final byte LONG = 5;
    protected static final byte DOUBLE = 6;
    protected static final byte NAME_AND_TYPE = 12;
    protected static final byte UTF8 = 1;
    protected static final byte METHOD_HANDLE = 15;
    protected static final byte METHOD_TYPE = 16;
    protected static final byte INVOKE_DYNAMIC = 18;
    protected byte tag;


    /**
     * return the constant pool info instance and
     * the number of bytes read by this method
     */
    public static Pair<ConstantPoolInfo, Integer> getConstantPoolInfo(byte[] classfile, int ofs) {
        ByteBuffer in = ByteBuffer.wrap(classfile, ofs, classfile.length - ofs);
        ConstantPoolInfo ret;
        byte tag = in.get();
        int bytesRead = 1;
        switch (tag) {
            case CLASS:
                ret = new ClassInfo(in.getShort());
                bytesRead += 2;
                break;
            case FIELD_REF: {
                ret = new FieldrefInfo(in.getShort(), in.getShort());
                bytesRead += 4;
                break;
            }
            case METHOD_REF: {
                ret = new MethodrefInfo(in.getShort(), in.getShort());
                bytesRead += 4;
                break;
            }
            case INTERFACE_METHOD_REF:
            {
                ret = new InterfaceMethodrefInfo(in.getShort(), in.getShort());
                bytesRead += 4;
                break;
            }
            case STRING:{
                ret = new StringInfo(in.getShort());
                bytesRead += 2;
                break;
            }

            case INTEGER:{
                ret = new IntegerInfo(read4Bytes(in));
                bytesRead += 4;
                break;
            }

            case FLOAT:{
                ret = new FloatInfo(read4Bytes(in));
                bytesRead += 4;
                break;
            }

            case LONG:{
                ret = new LongInfo(read4Bytes(in), read4Bytes(in));
                bytesRead += 8;
                break;
            }

            case DOUBLE:{
                ret = new DoubleInfo(read4Bytes(in), read4Bytes(in));
                bytesRead += 8;
                break;
            }
            case NAME_AND_TYPE:{
                ret = new NameAndTypeInfo(in.getShort(), in.getShort());
                bytesRead += 4;
                break;
            }

            case UTF8:{
                Pair<UTF8Info, Integer> utf8InfoIntegerPair = UTF8Info.getInstance(classfile, in.position());
                ret = utf8InfoIntegerPair.fst;
                bytesRead += utf8InfoIntegerPair.snd;
                break;
            }

            case METHOD_HANDLE:{
                ret = new MethodHandleInfo(in.get(), in.getShort());
                bytesRead += 3;
                break;
            }

            case METHOD_TYPE:{
                ret = new MethodTypeInfo(in.getShort());
                bytesRead += 2;
                break;
            }

            case INVOKE_DYNAMIC:{
                ret = new InvokeDynamicInfo(in.getShort(), in.getShort());
                bytesRead += 4;
                break;
            }

            default:
                throw new UnsupportedOperationException("Unsupported constant pool tag" + tag);
        }
        return new Pair<>(ret, bytesRead);
    }

    private static byte[] read4Bytes(ByteBuffer in) {
        byte b1 = in.get();
        byte b2 = in.get();
        byte b3 = in.get();
        byte b4 = in.get();
        return new byte[]{b1, b2, b3, b4};
    }

    public int getEntryLength(){
        return 1;
    }
}
