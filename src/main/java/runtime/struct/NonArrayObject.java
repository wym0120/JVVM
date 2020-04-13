package runtime.struct;

import lombok.Data;
import memory.jclass.JClass;
import runtime.Vars;

@Data
public class NonArrayObject extends JObject {

    private Vars fields;

    public NonArrayObject(JClass clazz) {
        assert clazz != null;
        this.clazz = clazz;
        fields = new Vars(clazz.getInstanceSlotCount());
    }

    public NonArrayObject(JClass clazz, boolean isNull) {
        this.clazz = clazz;
        this.isNull = isNull;
    }


}
