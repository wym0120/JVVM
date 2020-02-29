package runtime.struct;

import memory.jclass.JClass;
import runtime.Vars;

public class JObject {
    private JClass clazz;
    private Vars fields;
    private boolean isNull = false;

    public JObject() {

    }

    public JObject(boolean isNull) {
        this.isNull = isNull;
    }
}
