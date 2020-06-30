package minimal;

/**
 * Description:
 *
 * @author xxz
 * Created on 06/30/2020
 */
public class DarkMediumTest4Arith {

    public void runTest(int a, int b, int c){
        int result = 'w'+'y'+'m'+2333;
        TestUtil.equalInt(result, 2682);
        for (int i = 0; i < 20; i++) {
            result += a;
            result *= b;
            result ^= c;
        }
        for (int i = 0; i < 20; i++) {
            result /= b;
            result -= a;
        }
        TestUtil.equalInt(result, -75);
    }

    public static void main(String[] args) {
        DarkMediumTest4Arith test4Arith = new DarkMediumTest4Arith();
        test4Arith.runTest(73,29,31);
    }
}
