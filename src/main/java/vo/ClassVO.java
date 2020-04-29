package vo;

import memory.jclass.ClassMember;
import memory.jclass.JClass;
import runtime.Vars;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static memory.jclass.Field.parseDescriptor;

public class ClassVO extends HeapContentVO {
    private String className;
    private HashMap<String, String> staticMembers;
    private List<String> rtcp;

    public ClassVO(JClass clazz) {
        this.className = clazz.getName();
        if (!className.startsWith("[")) {
            this.staticMembers = parseStaticMembers(clazz);
//            this.rtcp =
        }
    }

    private HashMap<String, String> parseStaticMembers(JClass clazz) {
        HashMap<String, String> ret = new HashMap<>();
        Vars staticVars = clazz.getStaticVars();
        Arrays.stream(clazz.getFields())
                .filter(ClassMember::isStatic)
                .forEach(f -> {
                    String type = parseDescriptor(f.getDescriptor());
                    String name = f.getName();
                    int slotID = f.getSlotID();
                    ret.put("static " + type + " " + name, getInfo(staticVars, type, slotID));
                });
        return ret;
    }


    //parse rtcp
}
