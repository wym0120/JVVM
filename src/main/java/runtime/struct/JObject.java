package runtime.struct;

import lombok.Data;
import memory.jclass.JClass;

@Data
public abstract class JObject {
    protected static int numInHeap;
    protected int id;
    protected JClass clazz;
    protected boolean isNull = false;

    static {
        numInHeap = 0;
    }

    public JObject() {
        id = numInHeap;
        numInHeap++;
    }

    public boolean isInstanceOf(JClass clazz) {
        return this.clazz.isAssignableFrom(clazz);
    }
}
