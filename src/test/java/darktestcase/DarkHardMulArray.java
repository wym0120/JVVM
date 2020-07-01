package darktestcase;

import minimal.TestUtil;

public class DarkHardMulArray {
    char[] letters = new char[1];
    int[][] nums = new int[0][0];
    DarkHardMulArray[][] refs = new DarkHardMulArray[1][1];

    public static void main(String[] args) {
        DarkHardMulArray tmp = new DarkHardMulArray();
        tmp.letters = new char[]{'H','E','L','L','O','M','U','L','T','I','A','R','R','A','Y'};
        tmp.nums = new int[tmp.letters.length][tmp.letters.length-1];
        TestUtil.equalInt('O',tmp.letters[4]);
        tmp.nums[2][2] = 1;
        TestUtil.equalInt(1,tmp.nums[2][2]);
        TestUtil.equalInt(1,tmp.refs.length);
        tmp.refs = new DarkHardMulArray[3][3];
        tmp.refs[1][1] = new DarkHardMulArray();
        tmp.refs[1][1].letters=new char[]{'G','O','O','D','B','Y','E'};
        TestUtil.equalInt('Y',tmp.refs[1][1].letters[5]);
    }
}
