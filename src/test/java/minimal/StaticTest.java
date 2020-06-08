package minimal;

/**
 * Description:
 *
 * @author xxz
 * Created on 06/08/2020
 */

class Foo{
    public static int foo(){
        throw new NullPointerException();
    }
}


public class StaticTest {
    static {
        Foo.foo();
    }

    public static void main(String[] args) {

    }
}
