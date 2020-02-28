package memory;

import classloader.ClassLoader;

public class RuntimeMemory {
    private ClassLoader classLoader;
    private MethodArea methodArea;

    private RuntimeMemory() {
        classLoader = ClassLoader.getInstance();
    }


}
