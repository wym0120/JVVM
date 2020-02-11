import classloader.ClassLoader;
import org.junit.Test;

/**
 * Description:
 * Test for class loader
 * @author xxz
 * Created on 2020-02-10
 */

public class ClassLoaderTest {
    ClassLoader loader = new ClassLoader(null);

    @Test
    public void test() {
        System.out.println("Hi JVVM");
    }
}
