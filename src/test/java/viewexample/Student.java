package viewexample;

public class Student extends People {
    public Student(int id, int age) {
        this.id = id;
        this.age = age;
    }

    @Override
    public int solveMathProblem(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    public static void main(String[] args) {
        People xiaoming = new Student(1, 18);
        int[] nums = new int[]{1, 2};
        int res = xiaoming.solveMathProblem(nums);//res should equals 3
    }
}
