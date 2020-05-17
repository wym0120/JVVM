package vo;

import com.sun.tools.javac.util.Pair;
import memory.jclass.Field;
import runtime.Vars;
import runtime.struct.ArrayObject;
import runtime.struct.JObject;
import runtime.struct.NonArrayObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ObjectVO extends HeapContentVO {
    //<name, value>
    private Map<String, String> members;
    private String id;
    private boolean fresh;

    public ObjectVO(JObject obj, boolean fresh) {
        this.fresh = fresh;
        members = new LinkedHashMap<>();
        setBasic(obj);
    }

    private void setBasic(JObject obj) {
        String className = null;
        if (obj instanceof NonArrayObject) {
            className = obj.getClazz().getName();
            members = parseMembers(((NonArrayObject) obj).getFieldInfoList(), ((NonArrayObject) obj).getFields());
        } else if (obj instanceof ArrayObject) {
            className = ((ArrayObject) obj).getType();
            members = parseArray((ArrayObject) obj);
        }
        this.id = obj.getId() + "@" + className;
    }

    private Map<String, String> parseMembers(ArrayList<Pair<String, Integer>> fieldInfoList, Vars instanceVars) {
        Map<String, String> ret = new LinkedHashMap<>();
        fieldInfoList.forEach(info -> {
            String typeAndName = info.fst;
            int slotID = info.snd;
            String type = typeAndName.split(" ")[0];
            ret.put(typeAndName, getInfo(instanceVars, type, slotID));
        });
        return ret;
    }

    private Map<String, String> parseArray(ArrayObject obj) {
        Map<String, String> ret = new LinkedHashMap<>();
        ret.put("int len", "" + obj.getLen());
        String type = Field.parseDescriptor(obj.getType());
        String content = getArrayContent(obj);
        ret.put(type + " array", content);
        return ret;
    }
}
