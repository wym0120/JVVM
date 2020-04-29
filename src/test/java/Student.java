
public class Student extends People {
    private static int value1;
    private static double value2;
    private boolean hasHomework;


    public static void setValue() {
        value1 = 2;
        value2 = 3.5;
    }

    public Student(int id, int age) {
        this.id = id;
        this.age = age;
        hasHomework = true;
    }

    @Override
    public int solveMathProblem() {
        int[][] nums = new int[2][3];
        nums[0][0] = 1;
        int ret = countSum(countSum(nums), 2);
        hasHomework = false;
        return ret;
    }

    public int countSum(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        setValue();//invokeStatic
        int age = 22; //ldc
        Student xxz = new Student(123456, age);//new
        int id = xxz.id;//getfield
        if (xxz instanceof Student) {//instanceof
            xxz.id = 1456776728;//setfield
        }
        xxz = (Student) xxz;//checkcast
        xxz.solveMathProblem();//invokeVirtual
        if (!xxz.hasHomework) xxz.playGame();
        int value1 = Student.value1;//getStatic
        Student.value1 = value1 += 1;//setStatic
        xxz.doExtra();
        Play a = xxz;
        a.doExtra();

    }

    @Override
    public void doExtra() {

    }
}
