package memory;

import classloader.ClassLoader;

@Deprecated
public class RuntimeMemory {
    private ClassLoader classLoader;
    private MethodArea methodArea;

    private RuntimeMemory() {
        classLoader = ClassLoader.getInstance();
    }


}
