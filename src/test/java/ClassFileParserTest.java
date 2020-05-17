import com.njuse.seecjvm.classloader.classfileparser.ClassFile;
import com.njuse.seecjvm.classloader.classfilereader.ClassFileReader;
import com.njuse.seecjvm.memory.jclass.AccessFlags;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ClassFileParserTest {
    private static ClassFileReader reader;

    @BeforeAll
    static void init() {
        reader = ClassFileReader.getInstance();
        String testPath = String.join(File.separator, "src", "test", "testfile");
        ClassFileReader.setUserClasspath(testPath);
    }

    @DisplayName("validate result of parsing java.lang.Object")
    @Test
    @Disabled
    void parseObjectClass() {
        String className = String.join(File.separator, "java", "lang", "Object");
        byte[] content = assertDoesNotThrow(() -> {
            return reader.readClassFile(className, null).getKey();
        });

        ClassFile clazz = assertDoesNotThrow(() -> {
            return new ClassFile(content);
        });
        assertEquals(0, clazz.getMinorVersion());
        assertEquals(52, clazz.getMajorVersion());
        assertEquals(AccessFlags.ACC_PUBLIC | AccessFlags.ACC_SUPER, clazz.getAccessFlags());
        assertEquals(78, clazz.getConstantPoolCount());
        assertEquals(0, clazz.getSuperClass());
        assertEquals(0, clazz.getFieldsCount());
        assertEquals(className, clazz.getClassName().replace('/', File.separatorChar));
        assertThrows(UnsupportedOperationException.class, () -> System.out.println(clazz.getSuperClassName()));
        assertEquals(0, clazz.getInterfacesCount());
        for (String name : clazz.getInterfaceNames()) System.out.println(name);
        System.out.println();
        System.out.println("Method info : ");
        Arrays.stream(clazz.getMethods())
                .forEach(m -> {
                    System.out.println("name: " + m.getName() + "    descriptor: " + m.getDescriptor());
                    if (m.getCodeAttribute() != null) {
                        byte[] code = Optional.ofNullable(m.getCodeAttribute().getCode()).orElse(new byte[0]);
                        for (int bytecode : code) System.out.print(String.format("0x%08X", bytecode & 0xFF) + ", ");
                    }
                    System.out.println();
                    System.out.println();
                });
    }

    @DisplayName("validate result of parsing HelloWorld.class")
    @Test
    void parseHelloWorld() {
        String className = "HelloWorld";

        byte[] content = assertDoesNotThrow(() -> {
            return reader.readClassFile(className, null).getKey();
        });

        ClassFile clazz = assertDoesNotThrow(() -> {
            return new ClassFile(content);
        });
        assertEquals(0, clazz.getMinorVersion());
        assertEquals(52, clazz.getMajorVersion());
        assertEquals(AccessFlags.ACC_PUBLIC | AccessFlags.ACC_SUPER, clazz.getAccessFlags());
        assertEquals(29, clazz.getConstantPoolCount());
        assertEquals(6, clazz.getSuperClass());
        assertEquals(0, clazz.getFieldsCount());
        assertEquals(className, clazz.getClassName().replace('/', File.separatorChar));
        assertEquals("java/lang/Object", clazz.getSuperClassName());
        assertEquals(0, clazz.getInterfacesCount());
        for (String name : clazz.getInterfaceNames()) System.out.println(name);
        System.out.println();
        System.out.println("Method info : ");
        Arrays.stream(clazz.getMethods())
                .forEach(m -> {
                    System.out.println("name: " + m.getName() + "    descriptor: " + m.getDescriptor());
                    if (m.getCodeAttribute() != null) {
                        byte[] code = Optional.ofNullable(m.getCodeAttribute().getCode()).orElse(new byte[0]);
                        for (int bytecode : code) System.out.print(String.format("0x%08X", bytecode & 0xFF) + ", ");
                    }
                    System.out.println();
                });
    }
}
