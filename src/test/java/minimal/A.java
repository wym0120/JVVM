package minimal;

public class A implements B {
    public static void main(String[] args) {
        B obj = new A();
        obj.doSomething();
    }

    public void doSomething() {
        //do nothing
    }
}
