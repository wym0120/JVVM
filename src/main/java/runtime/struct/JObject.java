package runtime.struct;

import lombok.Data;
import memory.jclass.JClass;

@Data
public abstract class JObject {
    protected JClass clazz;
    protected boolean isNull = false;

    public boolean isInstanceOf(JClass clazz) {
        return this.clazz.isAssignableFrom(clazz);
    }
}
