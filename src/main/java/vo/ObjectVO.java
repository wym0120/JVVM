package vo;

import com.sun.tools.javac.util.Pair;
import runtime.Vars;
import runtime.struct.ArrayObject;
import runtime.struct.JObject;
import runtime.struct.NonArrayObject;
import runtime.struct.NullObject;
import runtime.struct.array.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Description:
 *
 * @author xiao bai
 * Created on 2020-04-14
 */
public class ObjectVO {
    //<name, value>
    private HashMap<String, String> members;
    private String id;
    private long size;

    public ObjectVO(JObject obj) {
        members = new HashMap<>();
        setBasic(obj);
    }

    private void setBasic(JObject obj) {
        String className = null;
        if (obj instanceof NonArrayObject) {
            className = obj.getClazz().getName();
            members = parseMembers(((NonArrayObject) obj).getFieldInfoList(), ((NonArrayObject) obj).getFields(), obj.getClazz().getStaticVars());
        } else if (obj instanceof ArrayObject) {
            className = ((ArrayObject) obj).getType();
            members = parseArray((ArrayObject) obj);
        }
        this.id = obj.getId() + "@" + className;
    }

    private HashMap<String, String> parseMembers(ArrayList<Pair<String, Integer>> fieldInfoList, Vars instanceVars, Vars staticVars) {
        HashMap<String, String> ret = new HashMap<>();
        fieldInfoList.forEach(info -> {
            String typeAndName = info.fst;
            int slotID = info.snd;
            String type = null;
            String name = null;
            if (typeAndName.startsWith("static")) {
                //[0] is "static"
                type = typeAndName.split(" ")[1];
                ret.put(typeAndName, getInfo(staticVars, type, slotID));
            } else {
                type = typeAndName.split(" ")[0];
                ret.put(typeAndName, getInfo(instanceVars, type, slotID));
            }
        });
        return ret;
    }

    private String getInfo(Vars slots, String type, int slotID) {
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

    private String getArrayContent(ArrayObject obj) {
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

    private HashMap<String, String> parseArray(ArrayObject obj) {
        HashMap<String, String> ret = new HashMap<>();
        ret.put("int len", "" + obj.getLen());
        String type = obj.parseDescriptor(obj.getType());
        String content = getArrayContent(obj);
        ret.put(type + " array", content);
        return ret;
    }

}
