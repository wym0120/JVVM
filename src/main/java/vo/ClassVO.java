package vo;

import memory.MethodArea;
import memory.jclass.ClassMember;
import memory.jclass.JClass;
import memory.jclass.runtimeConstantPool.constant.Constant;
import memory.jclass.runtimeConstantPool.constant.wrapper.DoubleWrapper;
import memory.jclass.runtimeConstantPool.constant.wrapper.LongWrapper;
import runtime.Vars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static memory.jclass.Field.parseDescriptor;

public class ClassVO extends HeapContentVO {
    private String className;
    private HashMap<String, String> staticMembers;
    private List<String> rtcp;
    private boolean fresh;

    public ClassVO(JClass clazz) {
        this.className = clazz.getName();
        this.fresh = MethodArea.getClassState().get(this.className);
        if (!className.startsWith("[")) {
            this.staticMembers = parseStaticMembers(clazz);
            this.rtcp = parseRTCP(clazz);
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

    private List<String> parseRTCP(JClass clazz) {
        List<String> ret = new ArrayList<>();
        Constant[] constants = clazz.getRuntimeConstantPool().getConstants();
        for (int i = 0; i < constants.length; i++) {
            ret.add("index: " + i + " " + constants[i].toString());
            if (constants[i] instanceof DoubleWrapper || constants[i] instanceof LongWrapper) i++;
        }
        return ret;
    }
}
