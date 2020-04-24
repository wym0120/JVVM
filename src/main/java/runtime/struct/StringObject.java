package runtime.struct;

import memory.jclass.JClass;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-04-24
 */
public class StringObject extends NonArrayObject {
    private String value;

    public StringObject(String value) {
        this.value = value;
    }

    @Override
    public boolean isInstanceOf(JClass clazz) {
        return clazz.getName().contains("String");
    }
}
