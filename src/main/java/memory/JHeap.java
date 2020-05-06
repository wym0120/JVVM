package memory;

import lombok.Getter;
import lombok.Setter;
import runtime.struct.JObject;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Setter
@Getter
public class JHeap {
    private static JHeap jHeap = new JHeap();
    private static Set<JObject> objects;
    private static Map<Integer, Boolean> objectState;//true to present object is new added

    public static JHeap getInstance() {
        return jHeap;
    }

    private JHeap() {
        objects = new LinkedHashSet<>();
        objectState = new LinkedHashMap<>();
    }

    public void addObj(JObject obj) {
        objects.add(obj);
        objectState.put(obj.getId(), true);
    }

    public Set<JObject> getObjects() {
        return objects;
    }

    public static Map<Integer, Boolean> getObjectState() {
        return objectState;
    }
}
