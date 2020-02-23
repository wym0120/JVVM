package classloader;

/**
 * Description:
 * Load class file
 * @author xxz
 * Created on 2020-02-10
 */
public class ClassLoader {
    private ClassFile classFile;
    private ClassFileReader classFileReader;

    public ClassLoader(byte[] classFile) {
        if (classFile == null) {
            throw new RuntimeException("Class file is null!");
        }
        this.classFile = new ClassFile(classFile);
    }

}
