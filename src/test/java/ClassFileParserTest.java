import classloader.classfileparser.ClassFile;
import classloader.classfilereader.ClassFileReader;
import memory.jclass.AccessFlags;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.*;

public class ClassFileParserTest {
    private static ClassFileReader reader;

    @BeforeAll
    static void init() {
        reader = ClassFileReader.getInstance();
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

        System.out.println(objectClass.getClassName());
        System.out.println(objectClass.getSuperClassName());
        System.out.println(Arrays.toString(objectClass.getInterfaceNames()));
        System.out.println("Method info : ");
        Arrays.stream(objectClass.getMethods())
                .forEach(m -> {
                    System.out.println(m.getName());
                    System.out.println(m.getDescriptor());
                    System.out.println(Arrays.toString(m.getCodeAttribute().getCode()));
                });
    }

    @DisplayName("validate result of parsing HelloWorld.class")
    @Test
    void parseHelloWorld() {

    }
}
