package com.njuse.seecjvm.classloader.classfilereader;

import com.njuse.seecjvm.classloader.classfilereader.classpath.*;
import com.njuse.seecjvm.util.PathUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class is the simulated implementation of Java Classloader.
 */
@Getter
@Setter
public class ClassFileReader {
    private static ClassFileReader reader = new ClassFileReader();
    private static final String FILE_SEPARATOR = File.separator;
    private static final String PATH_SEPARATOR = File.pathSeparator;

    private ClassFileReader() {
    }

    public static ClassFileReader getInstance() {
        return reader;
    }

    private static Entry bootClasspath = null;//bootstrap class entry
    private static Entry extClasspath = null;//extension class entry
    private static Entry userClasspath = null;//user class entry

    public static void setBootAndExtClasspath(String classpath) {
        bootClasspath = chooseEntryType(String.join(FILE_SEPARATOR, classpath, "lib", "*"));
        extClasspath = chooseEntryType(String.join(FILE_SEPARATOR, classpath, "lib", "ext", "*"));
    }

    public static void setUserClasspath(String classpath) {
        userClasspath = chooseEntryType(classpath);
    }

    public static String getUserClasspath() {
        return userClasspath.getClasspath();
    }

    public static String getXjrePath() {
        return extClasspath.getClasspath();
    }

    public static String getXbootPath() {
        return bootClasspath.getClasspath();
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
            return new ArchivedEntry(classpath);
        }
        return new DirEntry(classpath);
    }

    /**
     * read class file in privilege order
     * USER_ENTRY has highest privileges
     * If there is no relevant class loaded before
     * default privilege is USER_ENTRY
     *
     * @param className class to be read
     * @param privilege privilege of relevant class
     * @return content of class file and the privilege of loaded class
     */
    public Pair<byte[], Integer> readClassFile(String className, EntryType privilege) throws IOException, ClassNotFoundException {
        int value = (privilege == null) ? EntryType.USER_ENTRY : privilege.getValue();
        checkAndSetDefault();
        String realClassName = className + ".class";
        realClassName = PathUtil.transform(realClassName);
        byte[] data;
        if (value >= EntryType.BOOT_ENTRY) {
            if ((data = bootClasspath.readClass(realClassName)) != null) {
                return Pair.of(data, EntryType.BOOT_ENTRY);
            }
        }
        if (value >= EntryType.EXT_ENTRY) {
            if ((data = extClasspath.readClass(realClassName)) != null) {
                return Pair.of(data, EntryType.EXT_ENTRY);
            }
        }
        if (value >= EntryType.USER_ENTRY) {
            if ((data = userClasspath.readClass(realClassName)) != null) {
                return Pair.of(data, EntryType.USER_ENTRY);
            }
        }
        throw new ClassNotFoundException();
    }


    /**
     * Check all paths are not null
     * If not, set default paths for entries
     */
    private void checkAndSetDefault() throws FileNotFoundException {
        if (bootClasspath == null) {
            //set the bootClasspath together with extClasspath
        }

        if (extClasspath == null) {
            File f;

            f = new File(String.join(FILE_SEPARATOR, ".", "jre"));
            if (f.exists()) {
                setBootAndExtClasspath(String.join(FILE_SEPARATOR, ".", "jre"));
            } else {
                // Warning : You need to make sure the name of the environment variable is the same as in your system.
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
