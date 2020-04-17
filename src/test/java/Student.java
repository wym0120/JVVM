
public class Student extends People {
    private static int value1;
    private static double value2;
    private boolean hasHomework;


    public static void setValue() {
        value1 = 2;
        value2 = 3.5;
    }

    public Student(String name, int age) {
        this.name = name;
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

//    @Override
//    public void playGame() {
//        if (value1 >= 0) {
//            if (value1 < 3) {
//                if (value2 <= 4.0) {
//                    if (value2 > 3) {
//                        if (value2 + value1 == 5.5) {
//                            double res = value1 + value2 - value1 / value2 * value1;
//                            System.out.println(res);
//                        }
//                    }
//                }
//            }
//        }
//    }

    public static void main(String[] args) {
        setValue();//invokeStatic
        int age = 22; //ldc
        Student xxz = new Student("xxz", age);//new
        String name = xxz.name;//getfield
        if (xxz instanceof Student) {//instanceof
            xxz.name = "xxz again";//setfield
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
