package runtime.struct;

import lombok.Data;
import memory.jclass.JClass;
import runtime.Vars;

@Data
public class JObject {
    private JClass clazz;
    private Vars fields;
    private boolean isNull = false;

    public JObject(JClass clazz) {
        this.clazz = clazz;
        fields = new Vars(clazz.getInstanceSlotCount());
    }

    public JObject(JClass clazz, boolean isNull) {
        this(clazz);
        this.isNull = isNull;
    }

    public boolean isInstanceOf(JClass clazz) {
        return this.clazz.isAssignableFrom(clazz);
    }
}
