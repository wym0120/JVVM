package classpath;

import java.io.File;

public class ClassFileReader {
    private static ClassFileReader reader = new ClassFileReader();
    private static final String FILE_SEPARATOR = File.separator;

    private ClassFileReader() {
    }

    public static ClassFileReader getInstance() {
        return reader;
    }

    private static Entry bootClasspath = null;
    private static Entry extClasspath = null;
    private static Entry userClasspath = null;

    public static void setExtClasspath(String classpath) {
        bootClasspath = chooseEntryType(String.join(FILE_SEPARATOR,classpath,"lib","*"));
        extClasspath = chooseEntryType(String.join(FILE_SEPARATOR,classpath,"lib","ext","*") );

    }

    public static void setUserClasspath(String classpath) {
        userClasspath = chooseEntryType(classpath);
    }

    /**
     * @param classpath 类路径
     * @return 合适的路径筛选类
     */
    private static Entry chooseEntryType(String classpath) {
        if (classpath.contains(File.separator)) {
            return new CompositeEntry(classpath);
        }
        if (classpath.endsWith("*")) {
            return new CompositeEntry(classpath);
        }
        if (classpath.endsWith(".jar") || classpath.endsWith(".JAR")
                || classpath.endsWith(".zip") || classpath.endsWith(".ZIP")) {
            return new ZipEntry(classpath);
        }
        return new DirEntry(classpath);
    }

    public static byte[] readClassFile(String className) {
        checkCorrectClasspath();
        String realClassName = className + ".class";
        byte[] data = null;
        if((data = bootClasspath.readClass(realClassName))!=null){
            return data;
        }
        if((data = extClasspath.readClass(realClassName))!=null){
            return data;
        }
        return userClasspath.readClass(realClassName);
    }

    private static void checkCorrectClasspath(){
        if (bootClasspath == null) {

        }
        if (extClasspath == null) {

        }
        if (userClasspath == null) {
            userClasspath = chooseEntryType(".");
        }

    }

}
