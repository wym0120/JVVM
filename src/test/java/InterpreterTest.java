import classloader.ClassLoader;
import classloader.classfilereader.ClassFileReader;
import memory.MethodArea;
import memory.jclass.JClass;
import memory.jclass.Method;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import runtime.JThread;
import runtime.StackFrame;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class InterpreterTest {
    private static ClassLoader loader;

    @BeforeAll
    static void init() {
        loader = ClassLoader.getInstance();
//        String testPath = String.join(File.separator, "src", "test", "testfile", "student");
        String testPath = String.join(File.separator, "build", "classes", "java", "test");
        ClassFileReader.setUserClasspath(testPath);

    }

    @ParameterizedTest
//    @ValueSource(strings = {"Student"})
    @ValueSource(strings = {"minimal/A"})
    void Interpret(String className) {
        JClass clazz = assertDoesNotThrow(() -> {
            return loader.loadClass(className, null);
        });
        JThread thread = new JThread();
        Method main = clazz.getMainMethod();
        StackFrame mainFrame = new StackFrame(thread, main, main.getMaxStack(), main.getMaxStack());
        thread.pushFrame(mainFrame);
        Interpreter interpreter = new Interpreter();
        interpreter.interpret(thread);
    }

    @AfterAll
    static void printAll() {
        MethodArea.getClassMap().values().forEach(ClassLoaderTest::printInfo);
    }
}
