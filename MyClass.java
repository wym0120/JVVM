

interface foo{
    public default void M(){
        System.out.println("foo");
    }
}

interface bar extends foo{
//    public default void M(){
//        System.out.println("bar");
//    }
}

class A implements foo{
    static {
        System.out.println("AAA");
    }

    static void AM(){
        System.out.println("am");
    }
//    public void M(){
//        System.out.println("A");
//    }
}


class B implements foo{
    static {
        System.out.println("BBB");
    }

    static void AM(){
        System.out.println("amB");
    }
//    public void M(){
//        System.out.println("A");
//    }
}

public class MyClass extends A implements bar {
    public static final int i = 3;
    static long l = 3l;
    static final long fl = l;
    static double d = 3.0;
    static float f = 3.0f;

    public static void main(String args[]) {
        MyClass.l = 2;
        System.out.println("Hello world");
        new MyClass().M();
        B.AM();
    }


}
