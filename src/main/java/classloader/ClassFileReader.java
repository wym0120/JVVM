package classloader;

import classpath.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ClassFileReader {
    private static ClassFileReader reader = new ClassFileReader();
    private static final String FILE_SEPARATOR = File.separator;
    private static final String PATH_SEPARATOR = File.pathSeparator;

    private ClassFileReader() {
    }

    public static ClassFileReader getInstance() {
        return reader;
    }

    private static Entry bootClasspath = null;
    private static Entry extClasspath = null;
    private static Entry userClasspath = null;

    /**
     * Set Jre(ext) and XBootClasspath
     */
    public static void setExtClasspath(String classpath) {
        //todo:homework : implement bootClasspath
        bootClasspath = chooseEntryType(String.join(FILE_SEPARATOR, classpath, "lib", "*"));
        extClasspath = chooseEntryType(String.join(FILE_SEPARATOR, classpath, "lib", "ext", "*"));
    }

    public static void setUserClasspath(String classpath) {
        userClasspath = chooseEntryType(classpath);
    }

    /**
     * select Entry by type of classpath
     */
    public static Entry chooseEntryType(String classpath) {
        if (classpath.contains(PATH_SEPARATOR)) {
            return new CompositeEntry(classpath);
        }
        if (classpath.endsWith("*")) {
            return new WildEntry(classpath);
        }
        if (classpath.endsWith(".jar") || classpath.endsWith(".JAR")
                || classpath.endsWith(".zip") || classpath.endsWith(".ZIP")) {
            return new ZipEntry(classpath);
        }
        return new DirEntry(classpath);
    }

    public static byte[] readClassFile(String className) throws IOException {
        checkCorrectClasspath();
        String realClassName = className + ".class";
        byte[] data;
        if ((data = bootClasspath.readClass(realClassName)) != null && data.length!=0) {
            return data;
        }
        if ((data = extClasspath.readClass(realClassName)) != null && data.length!=0) {
            return data;
        }
        return userClasspath.readClass(realClassName);
    }

    /**
     * Check all paths are correct,if not,set default value
     */
    private static void checkCorrectClasspath() throws FileNotFoundException {
        if(bootClasspath == null){
            //todo:homework
        }

        if (extClasspath == null) {
            File f;

            f = new File(String.join(FILE_SEPARATOR, ".", "jre"));
            if (f.exists()) {
                setExtClasspath(String.join(FILE_SEPARATOR, ".", "jre"));
            }else{
                final String JAVA_HOME = System.getenv("JAVA_HOME");
                if (JAVA_HOME != null) {
                    f = new File(JAVA_HOME);
                    if (f.exists()) {
                        setExtClasspath(String.join(FILE_SEPARATOR, JAVA_HOME, "jre"));
                    }else{
                        throw new FileNotFoundException("Cannot find JRE folder!");
                    }
                }
            }
        }

        if (userClasspath == null) {
            setUserClasspath(".");
        }

    }

}
