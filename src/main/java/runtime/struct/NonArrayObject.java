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
        fields = new Vars(clazz.getInstanceSlotCount());
        fieldInfoList = new ArrayList<>();
        Arrays.stream(clazz.getFields()).forEach(f -> {
            String type = parseDescriptor(f.getDescriptor());
            String name = f.getName();
            int slotID = f.getSlotID();
            fieldInfoList.add(new Pair<>(type + " " + name, slotID));
        });
    }

    public NonArrayObject(JClass clazz, boolean isNull) {
        this.clazz = clazz;
        this.isNull = isNull;
    }

    //todo:
    public String parseDescriptor(String descriptor) {
        return "todo";
    }

}
