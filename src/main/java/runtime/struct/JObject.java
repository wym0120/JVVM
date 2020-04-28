package runtime.struct;

import lombok.Data;
import memory.jclass.JClass;

import java.util.Arrays;

@Data
public abstract class JObject {
    protected static int numInHeap;
    protected int id;
    protected JClass clazz;
    protected boolean isNull = false;

    static {
        numInHeap = 0;
    }

    public JObject() {
        id = numInHeap;
    }

    public boolean isInstanceOf(JClass clazz) {
        return this.clazz.isAssignableFrom(clazz);
    }

    public String parseDescriptor(String descriptor) {
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

    private String parseArrayName(String descriptor) {
        int dimensions = (int) Arrays.stream(descriptor.split(""))
                .filter(ch -> ch.equals("["))
                .count();
        String type = descriptor.substring(descriptor.lastIndexOf("[") + 1);
        StringBuilder ret = new StringBuilder(parseDescriptor(type));
        for (int i = 0; i < dimensions; i++) ret.append("[]");
        return ret.toString();
    }
}
