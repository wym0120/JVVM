package runtime.struct;

import com.sun.tools.javac.util.Pair;
import lombok.Data;
import memory.jclass.JClass;
import runtime.Vars;

import java.util.ArrayList;
import java.util.Arrays;

@Data
public class NonArrayObject extends JObject {

    private Vars fields;
    private ArrayList<Pair<String, Integer>> fieldInfoList;

    public NonArrayObject(JClass clazz) {
        assert clazz != null;
        this.clazz = clazz;
        numInHeap++;
        fields = new Vars(clazz.getInstanceSlotCount());
        fieldInfoList = new ArrayList<>();
        initDefaultValue(clazz);
        //todo:sort by index
        generateFieldInfoList(clazz);
    }

//    public NonArrayObject(JClass clazz, boolean isNull) {
//        this.clazz = clazz;
//        this.isNull = isNull;
//    }

    private void generateFieldInfoList(JClass clazz) {
        do {
            Arrays.stream(clazz.getFields())
                    .forEach(f -> {
                        String staticFlag = f.isStatic() ? "static " : "";
                        String type = parseDescriptor(f.getDescriptor());
                        String name = f.getName();
                        int slotID = f.getSlotID();
                        fieldInfoList.add(new Pair<>(staticFlag + type + " " + name, slotID));
                    });
            clazz = clazz.getSuperClass();
        } while (clazz != null);

    }

    private String parseDescriptor(String descriptor) {
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

    private void initDefaultValue(JClass clazz) {
        do {
            Arrays.stream(clazz.getFields())
                    .filter(f -> !f.isStatic())
                    .forEach(f -> {
                        String descriptor = f.getDescriptor();
                        switch (descriptor.charAt(0)) {
                            case 'Z':
                            case 'B':
                            case 'C':
                            case 'S':
                            case 'I':
                                this.fields.setInt(f.getSlotID(), 0);
                                break;
                            case 'F':
                                this.fields.setFloat(f.getSlotID(), 0);
                                break;
                            case 'J':
                                this.fields.setLong(f.getSlotID(), 0);
                                break;
                            case 'D':
                                this.fields.setDouble(f.getSlotID(), 0);
                                break;
                            default:
                                this.fields.setObjectRef(f.getSlotID(), new NullObject());
                                break;
                        }
                    });
            clazz = clazz.getSuperClass();
        } while (clazz != null);
    }
}
