package classpath;

import java.io.*;

public abstract class Entry {
    public final String PATH_SEPARATOR = File.pathSeparator;
    public final String FILE_SEPARATOR = File.separator;
    public String classpath;

    public Entry(String classpath){
        this.classpath = classpath;
    }
    public abstract byte[] readClass(String className) throws IOException;
}
