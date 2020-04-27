package vo;

import com.sun.tools.javac.util.Pair;
import runtime.Vars;
import runtime.struct.ArrayObject;
import runtime.struct.JObject;
import runtime.struct.NonArrayObject;

import java.util.ArrayList;
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
        setBasic(obj);
        members = new HashMap<>();
    }

    private void setBasic(JObject obj) {
        String className = null;
        if (obj instanceof NonArrayObject) {
            className = obj.getClazz().getName();
            members = parseMembers(((NonArrayObject) obj).getFieldInfoList(), ((NonArrayObject) obj).getFields());
        } else if (obj instanceof ArrayObject) {
            className = ((ArrayObject) obj).getType();
        }
        this.id = obj.getId() + "@" + className;
//        this.size = SizeCalculatorUtil.getObjectSize(obj);
    }

    private HashMap<String, String> parseMembers(ArrayList<Pair<String, Integer>> fieldInfoList, Vars slots) {
        HashMap<String, String> ret = new HashMap<>();
        fieldInfoList.forEach(info -> {
            String typeAndName = info.fst;
            int slotID = info.snd;
            String type = typeAndName.split(" ")[0];
            String name = typeAndName.split(" ")[1];
            switch (type) {
                case "byte":
                case "short":
                case "int":
                    ret.put(typeAndName, "" + slots.getInt(slotID));
                    break;
                case "char":
                    ret.put(typeAndName, "" + (char) (slots.getInt(slotID)));
                    break;
                case "boolean":
                    ret.put(typeAndName, (slots.getInt(slotID) == 0) ? "false" : "true");
                    break;
                case "float":
                    ret.put(typeAndName, "" + slots.getFloat(slotID));
                    break;
                case "long":
                    ret.put(typeAndName, "" + slots.getLong(slotID));
                    break;
                case "double":
                    ret.put(typeAndName, "" + slots.getDouble(slotID));
                    break;
                default:
                    ret.put(typeAndName, slots.getObjectRef(slotID).getClazz().getName());
                    break;
            }
        });
        return ret;
    }

    //todo:
    private HashMap<String, String> parseArray() {
        return null;
    }
}
