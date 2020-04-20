package minimal;

class Shape {
    public int edge() {
        return 0;
    }
}

class Circle extends Shape {

}

class Triangle extends Shape {
    @Override
    public int edge() {
        return super.edge() + 3;
    }
}

public class A implements B {
    public static void main(String[] args) {
        B obj = new A();
        //invoke interface
        obj.doSomething();
        //invoke virtual
        Shape triangle = new Triangle();
        int edge = triangle.edge();
    }

    public void doSomething() {
        //do nothing
    }
}
