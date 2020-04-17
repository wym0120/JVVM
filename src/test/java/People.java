public abstract class People implements Play {
    public int age;
    public String name;

    public abstract int solveMathProblem();

    public int countSum(int[][] nums) {
        int sum = 0;
        for (int[] num : nums) {
            for (int j = 0; j < nums[0].length; j++) {
                sum += num[j];
            }
        }
        return sum;
    }

    public void playGame() {

    }
}
