package minimal;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-05-31
 */
public class ConditionTest {
    public static void test(int small, int big, long smallL, long bigL, float smallF, float bigF,
                           double smallD, double bigD) {
        if (small == 3) {
            if (small < big && smallL < bigL && smallF < bigF && smallD < bigD) {

            } else {
                TestUtil.fail();
            }
            big++;
            if (big > small && bigL > smallL && bigF > smallF && bigD > smallD) {

            } else {
                TestUtil.fail();
            }
        } else {
            TestUtil.fail();
        }


        if (small <= big) {
            if (big > small) {

            } else {
                TestUtil.fail();
            }
            if (big + 1 >= small) {
                if (big == small) {
                    TestUtil.fail();
                }
                if (big != small) {

                } else {
                    TestUtil.fail();
                }
            }
        }
    }

    public static void main(String[] args) {
        test(3, 4, 5, 6, 7f, 8f, 9, 10);
    }
}
