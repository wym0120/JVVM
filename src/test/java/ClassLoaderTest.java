import classloader.ClassLoader;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

/**
 * Description:
 * Test for class loader
 * @author xxz
 * Created on 2020-02-10
 */

public class ClassLoaderTest {
    ClassLoader loader ;

    @Test
    public void test() throws IOException {
        System.out.println("Hi JVVM");
        File file = new File("MyClass.class");
        byte[] bytes = new byte[(int) file.length()];
        FileInputStream in = new FileInputStream(file);
        int num = in.read(bytes);
        assert num == file.length();
        loader = new ClassLoader(bytes);
    }
}
