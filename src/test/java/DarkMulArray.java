import minimal.TestUtil;

public class DarkMulArray {
    char[] letters = new char[1];
    int[][] nums = new int[0][0];
    DarkMulArray[][] refs = new DarkMulArray[1][1];

    public static void main(String[] args) {
        DarkMulArray tmp = new DarkMulArray();
        tmp.letters = new char[]{'H','E','L','L','O','M','U','L','T','I','A','R','R','A','Y'};
        tmp.nums = new int[tmp.letters.length][tmp.letters.length-1];
        TestUtil.equalInt('O',tmp.letters[4]);
        tmp.nums[2][2] = 1;
        TestUtil.equalInt(1,tmp.nums[2][2]);
        TestUtil.equalInt(1,tmp.refs.length);
        tmp.refs = new DarkMulArray[3][3];
        tmp.refs[1][1] = new DarkMulArray();
        tmp.refs[1][1].letters=new char[]{'G','O','O','D','B','Y','E'};
        TestUtil.equalInt('Y',tmp.refs[1][1].letters[5]);
    }
}
