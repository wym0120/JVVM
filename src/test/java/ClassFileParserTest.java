import classloader.classfileparser.ClassFile;
import classloader.classfilereader.ClassFileReader;
import memory.jclass.AccessFlags;
import org.junit.jupiter.api.BeforeAll;
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
    void parseObjectClass() {
        String className = String.join(File.separator, "java", "lang", "Object");
        byte[] content = assertDoesNotThrow(() -> {
            return reader.readClassFile(className, null).fst;
        });

        ClassFile objectClass = assertDoesNotThrow(() -> {
            return new ClassFile(content);
        });
        assertEquals(0, objectClass.getMinorVersion());
        assertEquals(52, objectClass.getMajorVersion());
        assertEquals(AccessFlags.ACC_PUBLIC | AccessFlags.ACC_SUPER, objectClass.getAccessFlags());
        assertEquals(78, objectClass.getConstantPoolCount());
        assertEquals(0, objectClass.getSuperClass());
        assertEquals(0, objectClass.getFieldsCount());
        assertEquals(className, objectClass.getClassName().replace('/', File.separatorChar));
        assertThrows(UnsupportedOperationException.class, () -> System.out.println(objectClass.getSuperClassName()));
        assertEquals(0, objectClass.getInterfacesCount());
        for (String name : objectClass.getInterfaceNames()) System.out.println(name);
        System.out.println();
        System.out.println("Method info : ");
        Arrays.stream(objectClass.getMethods())
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
            return reader.readClassFile(className, null).fst;
        });

        ClassFile objectClass = assertDoesNotThrow(() -> {
            return new ClassFile(content);
        });
        assertEquals(0, objectClass.getMinorVersion());
        assertEquals(52, objectClass.getMajorVersion());
        assertEquals(AccessFlags.ACC_PUBLIC | AccessFlags.ACC_SUPER, objectClass.getAccessFlags());
        assertEquals(29, objectClass.getConstantPoolCount());
        assertEquals(6, objectClass.getSuperClass());
        assertEquals(0, objectClass.getFieldsCount());
        assertEquals(className, objectClass.getClassName().replace('/', File.separatorChar));
        assertEquals("java/lang/Object", objectClass.getSuperClassName());
        assertEquals(0, objectClass.getInterfacesCount());
        for (String name : objectClass.getInterfaceNames()) System.out.println(name);
        System.out.println();
        System.out.println("Method info : ");
        Arrays.stream(objectClass.getMethods())
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
