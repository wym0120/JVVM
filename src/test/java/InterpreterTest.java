import com.njuse.seecjvm.classloader.ClassLoader;
import com.njuse.seecjvm.classloader.classfilereader.ClassFileReader;
import com.njuse.seecjvm.execution.Interpreter;
import com.njuse.seecjvm.memory.JHeap;
import com.njuse.seecjvm.memory.MethodArea;
import com.njuse.seecjvm.memory.jclass.JClass;
import com.njuse.seecjvm.memory.jclass.Method;
import com.njuse.seecjvm.runtime.JThread;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.util.JsonUtil;
import minimal.StaticTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class InterpreterTest {
    private static ClassLoader loader;

    @BeforeAll
    static void init() {
        loader = ClassLoader.getInstance();
        String testPath;
//        String systemName = System.getProperty("os.name");
//        if (systemName.startsWith("Windows")) {
//            testPath = String.join(File.separator, "build", "classes", "java", "test");
//        } else {
//            testPath = String.join(File.separator, "out", "test", "classes");
//        }
        testPath = String.join(File.separator, "build", "classes", "java", "test");

        ClassFileReader.setUserClasspath(testPath);

    }

    @ParameterizedTest
//    @ValueSource(strings = {"minimal/ConversionTest", "minimal/ConditionTest", "minimal/JmpTest", "minimal/MathTest", "minimal/InstructionTest"})
//    @ValueSource(strings = {"minimal/StaticTest"})
//    @ValueSource(strings = {"minimal/ArrayTestCase"})
//    @ValueSource(strings = {"minimal/DarkMediumTest4Arith"})
    @ValueSource(strings = {"darktestcase/DarkMNodeTest","darktestcase/DarkHardObjectInst","darktestcase/DarkHardMulArray"})
    void Interpret(String className) {
        JClass clazz = assertDoesNotThrow(() -> {
            return loader.loadClass(className, null);
        });
        JThread thread = new JThread();
        Method main = clazz.getMainMethod();
        StackFrame mainFrame = new StackFrame(thread, main, main.getMaxStack(), main.getMaxLocal());
        thread.pushFrame(mainFrame);
        JsonUtil.storeResult(className, Interpreter.interpret(thread));
        //reset memory
        MethodArea.reset();
        JHeap.reset();
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
