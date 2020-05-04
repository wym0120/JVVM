package vo;

import com.sun.tools.javac.util.Pair;
import memory.jclass.Field;
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
public class ObjectVO extends HeapContentVO {
    //<name, value>
    private HashMap<String, String> members;
    private String id;
    private boolean fresh;

    public ObjectVO(JObject obj, boolean fresh) {
        this.fresh = fresh;
        members = new HashMap<>();
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

    private HashMap<String, String> parseMembers(ArrayList<Pair<String, Integer>> fieldInfoList, Vars instanceVars) {
        HashMap<String, String> ret = new HashMap<>();
        fieldInfoList.forEach(info -> {
            String typeAndName = info.fst;
            int slotID = info.snd;
            String type = typeAndName.split(" ")[0];
            ;
            ret.put(typeAndName, getInfo(instanceVars, type, slotID));
        });
        return ret;
    }

    private HashMap<String, String> parseArray(ArrayObject obj) {
        HashMap<String, String> ret = new HashMap<>();
        ret.put("int len", "" + obj.getLen());
        String type = Field.parseDescriptor(obj.getType());
        String content = getArrayContent(obj);
        ret.put(type + " array", content);
        return ret;
    }
}
