package runtime.struct;

import com.sun.tools.javac.util.Pair;
import lombok.Getter;
import lombok.Setter;
import memory.jclass.JClass;
import runtime.Vars;

import java.util.ArrayList;
import java.util.Arrays;

import static memory.jclass.Field.parseDescriptor;

@Getter
@Setter
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
        generateInstanceFieldInfoList(clazz);
    }

    private void generateInstanceFieldInfoList(JClass clazz) {
        do {
            Arrays.stream(clazz.getFields())
                    .filter(f -> !f.isStatic())
                    .forEach(f -> {
                        String type = parseDescriptor(f.getDescriptor());
                        String name = f.getName();
                        int slotID = f.getSlotID();
                        fieldInfoList.add(new Pair<>(type + " " + name, slotID));
                    });
            clazz = clazz.getSuperClass();
        } while (clazz != null);

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
