import classloader.classfilereader.ClassFileReader;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClassFileReaderTest {
    public static List<String> targets;
    public static ClassFileReader reader;

    @BeforeAll
    static void InitClasspath() {
        reader = ClassFileReader.getInstance();
        targets = new LinkedList<>();
        String t1 = String.join(File.separator, "java", "lang", "Object");
        String t2 = String.join(File.separator, "java", "lang", "String");
        targets.add(t1);
        targets.add(t2);
    }

    @DisplayName("Read class from JDK in system environment variable")
    @Test
    void readFromJDK() {
        for (String className : targets) {
            if (System.getenv("JAVA_HOME") == null) {
                assertThrows(FileNotFoundException.class, () -> reader.readClassFile(className, null));
            } else {
                byte[] res = assertDoesNotThrow(() -> {
                    return reader.readClassFile(className, null).fst;
                });
                System.out.println(Arrays.toString(res));
            }
        }
    }

    @DisplayName("Read class from invalid user defined path")
    @Test
    void readFromUserDefined() {
        for (String className : targets) {
            ClassFileReader.setBootAndExtClasspath("Invalid jre path");
            ClassFileReader.setUserClasspath("Invalid user path");
            assertThrows(FileNotFoundException.class, () -> {
                reader.readClassFile(className, null);
            });
        }
    }

    @DisplayName("Read class from dir entry")
    @Test
    void readFromDir() {
        String classPath = String.join(File.separator, "src", "test", "testfile");
        String className = "test";
        ClassFileReader.setUserClasspath(classPath);
        assertDoesNotThrow(() -> {
            reader.readClassFile(className, null);
        });
    }

    @DisplayName("Read class from composite entry")
    @Test
    void compositeEntry() {
        String classPath1 = String.join(File.separator, "src", "test", "testfile");
        String classPath2 = String.join(File.separator, "src", "test", "testfile", "composite.zip");
        String className = "test";
        String classPath = String.join(File.pathSeparator, classPath1, classPath2);
        ClassFileReader.setUserClasspath(classPath);
        assertDoesNotThrow(() -> reader.readClassFile(className, null));
    }
}
