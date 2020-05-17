import classloader.ClassLoader;
import classloader.classfilereader.ClassFileReader;
import execution.Interpreter;
import memory.MethodArea;
import memory.jclass.JClass;
import memory.jclass.Method;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import runtime.JThread;
import runtime.StackFrame;
import util.JsonUtil;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class InterpreterTest {
    private static ClassLoader loader;

    @BeforeAll
    static void init() {
        //to trigger the compilation of test class
//        Class<InstructionTest> controlInstructionTestClass = InstructionTest.class;
        loader = ClassLoader.getInstance();
//        String testPath = String.join(File.separator, "src", "test", "testfile", "student");
        String testPath = String.join(File.separator, "build", "classes", "java", "test");
//        String testPath = String.join(File.separator, "out", "test", "classes" );
        ClassFileReader.setUserClasspath(testPath);

    }

    @ParameterizedTest
    @ValueSource(strings = {"viewexample/Student"})
    void Interpret(String className) {
        JClass clazz = assertDoesNotThrow(() -> {
            return loader.loadClass(className, null);
        });
        JThread thread = new JThread();
        Method main = clazz.getMainMethod();
        StackFrame mainFrame = new StackFrame(thread, main, main.getMaxStack(), main.getMaxLocal());
        thread.pushFrame(mainFrame);
        JsonUtil.storeResult(className, Interpreter.interpret(thread));
    }

    @AfterAll
    static void printAll() {
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("* * * * * * TEST FINISHED * * * * * * * * * *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");
        MethodArea.getClassMap().values().forEach(ClassLoaderTest::printInfo);
    }
}
