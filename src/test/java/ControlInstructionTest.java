/**
 * Description:
 *
 * @author xxz
 * Created on 2020-04-28
 */
public class ControlInstructionTest {
    private int result = -1;
    private boolean fail = false;

    private void fail() {
        fail = true;
    }

    private int run() {
        if (fail) {
            fail();
            return -1;
        } else {
            if (false) {
                fail();
                return -1;
            } else if (false) {
                fail();
                return -1;
            } else {
                if (!fail) {
                    if (true) {
                        result++;
                        return result;
                    }
                    fail();
                    return -1;
                } else {
                    return -1;
                }
            }
        }
    }

    public void testArithmetic() {
        char zero = '0';
        byte a = 1, b = 2;
        short c = 3;
        int d = 4;
        long e = 2147483647;
        float f = 6.5f;
        double g = -7.5;
        //test add
        int tmp1 = zero + a;
        if (tmp1 != '1') {
            fail();
            result = 1;
            return;
        }
        tmp1 = a + b;
        if (tmp1 != 3) {
            fail();
            result = 2;
            return;
        }
        if (c + c != 6) {
            fail();
            result = 3;
            return;
        }
        if (d + d != 8) {
            fail();
            result = 4;
            return;
        }
        if (e + 2 < 0) {
            fail();
            result = 5;
            return;
        } else if (e + 2 != 2147483649L) {
            fail();
            result = 6;
            return;
        }
        if (f + f > 13.0001) {
            fail();
            result = 7;
            return;
        } else if (f + f < 12.9999) {
            fail();
            result = 7;
            return;
        }

        if (g + f < -1.0001) {
            fail();
            result = 8;
            return;
        } else if (g + f > -0.9999) {
            fail();
            result = 8;
            return;
        }

    }

    public static void main(String[] args) {
        ControlInstructionTest tester = new ControlInstructionTest();
        int ret = tester.run();
        if (!tester.fail) {
            tester.testArithmetic();
            ret = tester.result;
        }
    }

}
