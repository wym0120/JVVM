import classloader.ClassLoader;
import classloader.classfilereader.ClassFileReader;
import memory.jclass.JClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class ClassLoaderTest {
    private static ClassLoader loader;

    @BeforeAll
    static void init() {
        loader = ClassLoader.getInstance();
        String testPath = String.join(File.separator, "src", "test", "testfile");
        ClassFileReader.setUserClasspath(testPath);
    }

    @Test
    void JClassTest() {
        JClass helloWorldClass = assertDoesNotThrow(() -> {
            return loader.loadClass("HelloWorld", null);
        });

    }
}
