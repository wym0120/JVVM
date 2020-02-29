package classloader.classfilereader;

import classloader.classfilereader.classpath.*;
import com.sun.tools.javac.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author WYM
 * This class is the simulated implementation of Java Classloader.
 */
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
    public static void setBootAndExtClasspath(String classpath) {
        bootClasspath = chooseEntryType(String.join(FILE_SEPARATOR, classpath, "lib", "*"));
        extClasspath = chooseEntryType(String.join(FILE_SEPARATOR, classpath, "lib", "ext", "*"));
    }

    public static void setUserClasspath(String classpath) {
        userClasspath = chooseEntryType(classpath);
    }

    /**
     * select Entry by type of classloader.classfilereader.classpath
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

//    public Pair<byte[],Integer> readClassFile(String className) throws IOException, ClassNotFoundException {
//        checkCorrectClasspath();
//        String realClassName = className + ".class";
//        byte[] data;
//        if ((data = bootClasspath.readClass(realClassName)) != null && data.length!=0) {
//            return new Pair<>(data,EntryType.BOOT_ENTRY);
//        }
//        if ((data = extClasspath.readClass(realClassName)) != null && data.length!=0) {
//            return new Pair<>(data,EntryType.EXT_ENTRY);
//        }
//        if((data = userClasspath.readClass(realClassName)) != null && data.length!=0){
//            return new Pair<>(data,EntryType.USER_ENTRY);
//        }
//        throw new ClassNotFoundException();
//    }

    public Pair<byte[], Integer> readClassFile(String className, EntryType type) throws IOException, ClassNotFoundException {
        int value = (type == null) ? EntryType.USER_ENTRY : type.getValue();
        checkCorrectClasspath();
        String realClassName = className + ".class";
        byte[] data;
        if (value >= EntryType.BOOT_ENTRY) {
            if ((data = bootClasspath.readClass(realClassName)) != null && data.length != 0) {
                return new Pair<>(data, EntryType.BOOT_ENTRY);
            }
        }
        if (value >= EntryType.EXT_ENTRY) {
            if ((data = extClasspath.readClass(realClassName)) != null && data.length != 0) {
                return new Pair<>(data, EntryType.EXT_ENTRY);
            }
        }
        if (value >= EntryType.USER_ENTRY) {
            if ((data = userClasspath.readClass(realClassName)) != null && data.length != 0) {
                return new Pair<>(data, EntryType.USER_ENTRY);
            }
        }
        throw new ClassNotFoundException();
    }


    /**
     * Check all paths are correct,if not,set default value
     */
    private void checkCorrectClasspath() throws FileNotFoundException {
        if (bootClasspath == null) {
            //specify the bootClasspath together with extClasspath
        }

        if (extClasspath == null) {
            File f;

            f = new File(String.join(FILE_SEPARATOR, ".", "jre"));
            if (f.exists()) {
                setBootAndExtClasspath(String.join(FILE_SEPARATOR, ".", "jre"));
            } else {
                final String JAVA_HOME = System.getenv("JAVA_HOME");
                if (JAVA_HOME != null) {
                    f = new File(JAVA_HOME);
                    if (f.exists()) {
                        setBootAndExtClasspath(String.join(FILE_SEPARATOR, JAVA_HOME, "jre"));
                    } else {
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
