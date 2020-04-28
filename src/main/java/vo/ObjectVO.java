package vo;

import com.sun.tools.javac.util.Pair;
import runtime.Vars;
import runtime.struct.ArrayObject;
import runtime.struct.JObject;
import runtime.struct.NonArrayObject;
import runtime.struct.NullObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

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
                else if (obj instanceof ArrayObject) return "todo";
                throw new RuntimeException("Invalid field type");
        }
    }

    //todo:
    private HashMap<String, String> parseArray(ArrayObject obj) {
        String type = obj.getType();
        int dimensions = (int) Stream.of(type.split("")).filter(ch -> ch.equals("[")).count();
        return null;
    }

}
