package minimal;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-06-01
 */
public class MathTest {

    public static void test(int a, int b) {
        if (a + b == 11 && a - b == 1 && a * b == 30 && a / b == 1) {
            TestUtil.equalInt(a, 6);
            TestUtil.equalInt(b, 5);
        } else {
            TestUtil.fail();
        }

    }

    public static void main(String[] args) {
        test(6, 5);
    }
}
