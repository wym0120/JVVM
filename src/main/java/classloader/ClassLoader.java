package classloader;

/**
 * Description:
 * Load class file
 * @author xxz
 * Created on 2020-02-10
 */
public class ClassLoader {
    private Byte[] classFile;
    public ClassLoader(Byte[] classFile) {
        if (classFile == null) {
            throw new RuntimeException("Class file is null!");
        }
        this.classFile = classFile;
    }

}
