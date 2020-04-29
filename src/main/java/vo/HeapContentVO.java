package vo;

import runtime.Vars;
import runtime.struct.ArrayObject;
import runtime.struct.JObject;
import runtime.struct.NonArrayObject;
import runtime.struct.NullObject;
import runtime.struct.array.*;

import java.util.Arrays;

public class HeapContentVO {

    public String getInfo(Vars slots, String type, int slotID) {
        switch (type) {
            case "byte":
            case "short":
            case "int":
                return "" + slots.getInt(slotID);
            case "char":
                return "" + (char) (slots.getInt(slotID));
            case "boolean":
                return (slots.getInt(slotID) == 0) ? "false" : "true";
            case "float":
                return "" + slots.getFloat(slotID);
            case "long":
                return "" + slots.getLong(slotID);
            case "double":
                return "" + slots.getDouble(slotID);
            default:
                JObject obj = slots.getObjectRef(slotID);
                if (obj instanceof NullObject) return "null";
                else if (obj instanceof NonArrayObject)
                    return slots.getObjectRef(slotID).getId() + "@" + obj.getClazz().getName();
                else if (obj instanceof ArrayObject) return getArrayContent((ArrayObject) obj);
                throw new RuntimeException("Invalid field type");
        }
    }

    public String getArrayContent(ArrayObject obj) {
        String type = obj.getType();
        switch (type) {
            case "[Z":
                return Arrays.toString(((BooleanArrayObject) obj).getArray());
            case "[B":
                return Arrays.toString(((ByteArrayObject) obj).getArray());
            case "[C":
                return Arrays.toString(((CharArrayObject) obj).getArray());
            case "[S":
                return Arrays.toString(((ShortArrayObject) obj).getArray());
            case "[I":
                return Arrays.toString(((IntArrayObject) obj).getArray());
            case "[J":
                return Arrays.toString(((LongArrayObject) obj).getArray());
            case "[F":
                return Arrays.toString(((FloatArrayObject) obj).getArray());
            case "[D":
                return Arrays.toString(((DoubleArrayObject) obj).getArray());
            default:
                assert obj instanceof RefArrayObject;
                if (obj.getType().lastIndexOf('[') != 0) {
                    StringBuilder ret = new StringBuilder();
                    for (int i = 0; i < obj.getLen(); i++) {
                        ret.append(getArrayContent((ArrayObject) ((RefArrayObject) obj).getArray()[i]));
                        ret.append(",");
                    }
                    return "[" + ret.substring(0, ret.length() - 1) + "]";
                }
                return Arrays.toString(((RefArrayObject) obj).getArray());
        }
    }

}
