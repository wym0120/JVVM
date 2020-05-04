package memory;

import lombok.Getter;
import lombok.Setter;
import runtime.struct.JObject;

import java.util.HashMap;
import java.util.HashSet;

@Setter
@Getter
public class JHeap {
    private static JHeap jHeap = new JHeap();
    private static HashSet<JObject> objects;
    private static HashMap<Integer, Boolean> objectState;//true to present object is new added

    public static JHeap getInstance() {
        return jHeap;
    }

    private JHeap() {
        objects = new HashSet<>();
        objectState = new HashMap<>();
    }

    public void addObj(JObject obj) {
        objects.add(obj);
        objectState.put(obj.getId(), true);
    }

    public HashSet<JObject> getObjects() {
        return objects;
    }

    public static HashMap<Integer, Boolean> getObjectState() {
        return objectState;
    }
}
