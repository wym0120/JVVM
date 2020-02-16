package classloader.attribute;

import classloader.constantpool.ConstantPool;
import classloader.constantpool.info.ConstantPoolInfo;
import classloader.constantpool.info.UTF8Info;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class AttributeBuilder {
    public static final String AnnotationDefault = "AnnotationDefault";
    public static final String BootstrapMethods = "BootstrapMethods";
    public static final String CharacterRangeTable = "CharacterRangeTable";
    public static final String Code = "Code";
    public static final String ConstantValue = "ConstantValue";
    public static final String CompilationID = "CompilationID";
    public static final String Deprecated = "Deprecated";
    public static final String EnclosingMethod = "EnclosingMethod";
    public static final String Exceptions = "Exceptions";
    public static final String InnerClasses = "InnerClasses";
    public static final String LineNumberTable = "LineNumberTable";
    public static final String LocalVariableTable = "LocalVariableTable";
    public static final String LocalVariableTypeTable = "LocalVariableTypeTable";
    public static final String MethodParameters = "MethodParameters";
    public static final String RuntimeVisibleAnnotations = "RuntimeVisibleAnnotations";
    public static final String RuntimeInvisibleAnnotations = "RuntimeInvisibleAnnotations";
    public static final String RuntimeVisibleParameterAnnotations = "RuntimeVisibleParameterAnnotations";
    public static final String RuntimeInvisibleParameterAnnotations = "RuntimeInvisibleParameterAnnotations";
    public static final String RuntimeVisibleTypeAnnotations = "RuntimeVisibleTypeAnnotations";
    public static final String RuntimeInvisibleTypeAnnotations = "RuntimeInvisibleTypeAnnotations";
    public static final String Signature = "Signature";
    public static final String SourceDebugExtension = "SourceDebugExtension";
    public static final String SourceFile = "SourceFile";
    public static final String SourceID = "SourceID";
    public static final String StackMap = "StackMap";
    public static final String StackMapTable = "StackMapTable";
    public static final String Synthetic = "Synthetic";
    private static Map<String, Class<? extends AttributeInfo>> standardAttributes;

    private static void init() {
        standardAttributes = new HashMap<>();
        standardAttributes.put(ConstantValue, ConstantValueAttr.class);
    }

    public static AttributeInfo createAttribute(ConstantPool cp, ByteBuffer buffer) {
        if (standardAttributes == null) {
            init();
        }

        short attributeNameAndIndex = buffer.getShort();
        int attributeLength = buffer.getInt();

        ConstantPoolInfo constantPoolInfo = cp.get(attributeNameAndIndex);
        if (constantPoolInfo instanceof UTF8Info) {
            String attrName = ((UTF8Info) constantPoolInfo).getString();
            Class<? extends AttributeInfo> attrClz = standardAttributes.get(attrName);
            if (attrClz != null) {
                try {
                    Constructor<? extends AttributeInfo> constructor = attrClz.getDeclaredConstructor(
                            ByteBuffer.class, Short.TYPE, Integer.TYPE);
                    return constructor.newInstance(buffer, attributeNameAndIndex, attributeLength);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new UnsupportedOperationException("Error when parsing attribute " + attrName);
                }
            } else {
                //default attr info
                return new AttributeInfo(buffer, attributeNameAndIndex, attributeLength);
            }
        } else {
            throw new UnsupportedOperationException("The nameIndex of attribute is not a UTF-8 string!");
        }
    }

}
