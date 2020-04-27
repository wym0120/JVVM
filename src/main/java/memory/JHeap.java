package memory;

import lombok.Getter;
import lombok.Setter;
import runtime.struct.JObject;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class JHeap {
    private static JHeap jHeap = new JHeap();
    private static Set<JObject> heap;

    public static JHeap getInstance() {
        return jHeap;
    }

    private JHeap() {
        heap = new HashSet<>();
    }

    public void addObj(JObject obj) {
        heap.add(obj);
    }

    public Set<JObject> getHeap() {
        return heap;
    }
}
