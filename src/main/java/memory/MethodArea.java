package memory;

import lombok.Data;
import memory.jclass.JClass;

import java.util.HashMap;
import java.util.Map;

@Data
public class MethodArea {
    private static MethodArea methodArea = new MethodArea();

    private MethodArea() {
        classMap = new HashMap<>();
        classState = new HashMap<>();
    }

    private static Map<String, JClass> classMap;
    private static Map<String, Boolean> classState;//true presents this class is new added

    public static MethodArea getInstance() {
        return methodArea;
    }

    public JClass findClass(String className) {
        if (classMap.keySet().stream().anyMatch(name -> name.equals(className))) {
            return classMap.get(className);
        } else {
            return null;
        }
    }

    public void addClass(String className, JClass clazz) {
        classMap.put(className, clazz);
        classState.put(className, true);
    }

    public static Map<String, JClass> getClassMap() {
        return classMap;
    }

    public static Map<String, Boolean> getClassState() {
        return classState;
    }
}
