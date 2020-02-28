package memory;

import memory.jclass.JClass;

import java.util.HashMap;
import java.util.Map;

public class MethodArea {
    private static MethodArea methodArea = new MethodArea();

    private MethodArea() {
        classMap = new HashMap<>();
    }

    private static Map<String, JClass> classMap;

    public static MethodArea getInstance() {
        return methodArea;
    }

    public JClass findClass(String className) {
        return classMap.getOrDefault(className, null);
    }

    public void addClass(String className, JClass clazz) {
        classMap.put(className, clazz);
    }
}
