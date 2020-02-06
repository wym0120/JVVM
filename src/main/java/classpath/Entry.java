package classpath;

import java.io.File;

public abstract class Entry {
    final String pathSeparator = File.pathSeparator;
    private String classpath;

    public Entry(String classpath){
        this.classpath = classpath;
    }
    public abstract byte[] readClass(String className);
}
