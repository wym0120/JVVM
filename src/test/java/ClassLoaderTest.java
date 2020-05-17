import com.njuse.seecjvm.classloader.ClassLoader;
import com.njuse.seecjvm.classloader.classfilereader.ClassFileReader;
import com.njuse.seecjvm.memory.MethodArea;
import com.njuse.seecjvm.memory.jclass.JClass;
import com.njuse.seecjvm.util.ColorUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ClassLoaderTest {
    private static ClassLoader loader;

    @BeforeAll
    static void init() {
        loader = ClassLoader.getInstance();
        String testPath = String.join(File.separator, "src", "test", "testfile");
        ClassFileReader.setUserClasspath(testPath);
    }

    @ParameterizedTest
    @ValueSource(strings = {"HelloWorld", "Dog"})
    void loadNonArrayClassTest(String className) {
        assertDoesNotThrow(() -> {
            loader.loadClass(className, null);
        });
    }

    @AfterAll
    static void printAll() {
        MethodArea.getClassMap().values().forEach(ClassLoaderTest::printInfo);
    }

    public static void printInfo(JClass clazz) {
        //judge array or nonarray
        boolean isNonArrayClass = clazz.getName().charAt(0) != '[';
        if (!isNonArrayClass) {
            System.out.println("Class Name: " + clazz.getName());
        } else {
            System.out.println("Package Name: " + clazz.getPackageName());
            System.out.println("Superclass Name: " + clazz.getSuperClassName());
            System.out.println("Name of interfaces: ");
            Arrays.stream(clazz.getInterfaceNames()).forEach(System.out::printf);
            System.out.println();
            System.out.println("Field Info: ");
            Arrays.stream(clazz.getMethods()).forEach(f -> System.out.println(
                    "Field Name:" + f.getName() +
                            " descriptor:" + f.getDescriptor() +
                            " accessFlag:" + f.getAccessFlags()));
            System.out.println();
            System.out.println("Method Info:");
            Arrays.stream(clazz.getMethods()).forEach(m -> System.out.println(
                    "Method Name:" + m.getName() +
                            " descriptor:" + m.getDescriptor() +
                            " accessFlag:" + m.getAccessFlags() +
                            " maxStack: " + m.getMaxLocal() +
                            " maxLocal: " + m.getMaxLocal()));
            System.out.println();
            System.out.println("Init State: " + clazz.getInitState());
            System.out.println("Access Flags:" + String.format("0x%08X", clazz.getAccessFlags()));
            System.out.println("Load by: " + clazz.getLoadEntryType().getValue());
            System.out.println("Count of instances slot: " + clazz.getInstanceSlotCount());
            System.out.println("Count of static slot: " + clazz.getStaticSlotCount());
        }
        System.out.println();
        ColorUtil.printBlue("------------------------------------------------------------------------------------------------------");
        System.out.println();
    }
}
