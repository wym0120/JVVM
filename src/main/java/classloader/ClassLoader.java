package classloader;

import classpath.EntryType;
import com.sun.tools.javac.util.Pair;
import memory.MethodArea;
import memory.jclass.JClass;

import java.io.IOException;

/**
 * Description:
 * Load class file
 *
 * @author xxz
 * Created on 2020-02-10
 */
public class ClassLoader {
    private static ClassLoader classLoader = new ClassLoader();
    private ClassFileReader classFileReader;
    private MethodArea methodArea;

    private ClassLoader() {
        classFileReader = ClassFileReader.getInstance();
        methodArea = MethodArea.getInstance();
    }

    public static ClassLoader getInstance() {
        return classLoader;
    }


    /**
     * @param className       name of class
     * @param initiatingEntry null value represents load MainClass
     * @throws ClassNotFoundException cnf
     */
    public void loadClass(String className, EntryType initiatingEntry) throws ClassNotFoundException {
        if (methodArea.findClass(className) == null) {
            //array and nonarray
            loadNonArrayClass(className, initiatingEntry);
        }
    }

    private void loadNonArrayClass(String className, EntryType initiatingEntry) throws ClassNotFoundException {
        try {
            Pair<byte[], Integer> res = classFileReader.readClassFile(className, initiatingEntry);
            byte[] data = res.fst;
            EntryType definingEntry = new EntryType(res.snd);
            //define class
            JClass clazz = defineClass(data, definingEntry);
            //link class
            linkClass(clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }

    private void loadArrayClass() {

    }

    private JClass defineClass(byte[] data, EntryType definingEntry) {
        //todo:create classfile need to handle java.lang.ClassFormatError
        ClassFile classFile = new ClassFile(data);
        JClass clazz = new JClass(classFile);
        clazz.setLoadEntryType(definingEntry);
        resolveSuperClass(clazz);
        resolveInterfaces(clazz);
        methodArea.addClass(clazz.getName(), clazz);
        return clazz;
    }

    private void resolveSuperClass(JClass clazz) {
    }

    private void resolveInterfaces(JClass clazz) {

    }

    private void linkClass(JClass clazz) {
        verify(clazz);
        prepare(clazz);
    }

    /**
     * todo:homework
     */
    private void verify(JClass clazz) {
        //do nothing
    }

    private void prepare(JClass clazz) {

    }
}
