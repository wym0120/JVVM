import classloader.classfileparser.ClassFile;
import classloader.classfilereader.ClassFileReader;
import memory.jclass.JClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@Deprecated
public class JClassTest {
    private static ClassFileReader reader;

    @BeforeAll
    static void init() {
        reader = ClassFileReader.getInstance();
        String testPath = String.join(File.separator, "src", "test", "testfile");
        ClassFileReader.setUserClasspath(testPath);
    }

    @Test
    void createHelloWorldClass() {
        String className = "HelloWorld";
        byte[] content = assertDoesNotThrow(() -> {
            return reader.readClassFile(className, null).fst;
        });
        ClassFile clazz = assertDoesNotThrow(() -> {
            return new ClassFile(content);
        });

        JClass helloWorldClass = new JClass(clazz);
        System.out.println(helloWorldClass.getName());
        System.out.println(helloWorldClass.getArrayClass());
        System.out.println(helloWorldClass.getSuperClass().getName());
    }
}
