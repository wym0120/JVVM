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
     * 设置JRE路径，当前实现中启动类路径于扩展类路径是一致的
     * @param classpath 类路径
     */
    public static void setExtClasspath(String classpath) {
        //todo:这里bootClasspath和ext保持一致，作业中需要单独实现bootClasspath
        bootClasspath = chooseEntryType(String.join(FILE_SEPARATOR, classpath, "lib", "*"));
        extClasspath = chooseEntryType(String.join(FILE_SEPARATOR, classpath, "lib", "ext", "*"));
    }

    /**
     * 设置用户类路径
     * @param classpath 类路径
     */
    public static void setUserClasspath(String classpath) {
        userClasspath = chooseEntryType(classpath);
    }

    /**
     * 根据类路径的类型来选择合适的路经筛选类
     * @param classpath 类路径
     * @return 合适的路径筛选类
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
     * 检查三种路径是否都是合法路径，若不合法则设置为默认值
     */
    private static void checkCorrectClasspath() throws FileNotFoundException {
        if(bootClasspath == null){
            //todo:作业
        }

        if (extClasspath == null) {
            File f;

            //判断为当前目录下是否存在jre路径
            f = new File(String.join(FILE_SEPARATOR, ".", "jre"));
            if (f.exists()) {
                setExtClasspath(String.join(FILE_SEPARATOR, ".", "jre"));
            }else{
                //判断是否有环境变量并且能找到jre路径
                final String JAVA_HOME = System.getProperty("JAVA_HOME");
                if (JAVA_HOME != null) {
                    f = new File(JAVA_HOME);
                    if (f.exists()) {
                        setExtClasspath(String.join(FILE_SEPARATOR, JAVA_HOME, "jre"));
                    }else{
                        //若都不存在则抛出异常
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
