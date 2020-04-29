package memory.jclass;

import classloader.classfileparser.FieldInfo;
import lombok.Data;

import java.util.Arrays;

@Data
public class Field extends ClassMember {
    private int slotID;
    private int constValueIndex;

    public Field(FieldInfo info, JClass clazz) {
        this.clazz = clazz;
        accessFlags = info.getAccessFlags();
        name = info.getName();
        descriptor = info.getDescriptor();
        if (info.getConstantValueAttr() != null) {
            constValueIndex = info.getConstantValueAttr().getConstantValueIndex();
        }
    }

    public static String parseDescriptor(String descriptor) {
        switch (descriptor.charAt(0)) {
            case 'Z':
                return "boolean";
            case 'B':
                return "byte";
            case 'C':
                return "char";
            case 'S':
                return "short";
            case 'I':
                return "int";
            case 'F':
                return "float";
            case 'J':
                return "long";
            case 'D':
                return "double";
            case 'L':
                String name = descriptor.substring(1, descriptor.length() - 1);
                String[] tmp = name.split("/");
                name = tmp[tmp.length - 1];//simple name
                return name;
            case '[':
                return parseArrayName(descriptor);
            default:
                throw new RuntimeException("Invalid field descriptor.");
        }
    }

    private static String parseArrayName(String descriptor) {
        int dimensions = (int) Arrays.stream(descriptor.split(""))
                .filter(ch -> ch.equals("["))
                .count();
        String type = descriptor.substring(descriptor.lastIndexOf("[") + 1);
        StringBuilder ret = new StringBuilder(parseDescriptor(type));
        for (int i = 0; i < dimensions; i++) ret.append("[]");
        return ret.toString();
    }
}
