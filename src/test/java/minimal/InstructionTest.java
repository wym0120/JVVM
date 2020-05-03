package minimal;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-04-28
 */
public class InstructionTest {
    private int result = -1;
    private boolean fail = false;

    private void fail() {
        fail = true;
        TestUtil.fail();
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

    public void testAdd() {
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

    public void testConversion(float flt, double db, double bigDB, float bigFLT) {
        //d2f
        float a = (float) db;
        float b = flt;
        TestUtil.equalFloat(a, b);
        //d2i
        int c = (int) db;
        //f2i
        int d = (int) flt;
        TestUtil.equalInt(c, d);
        TestUtil.equalInt(c, 3);
        //d2i
        int max = (int) bigDB;
        TestUtil.equalInt(max, Integer.MAX_VALUE);
        //d2L
        long maxL = (long) bigDB;
        if (maxL != 2147483648L) {
            TestUtil.fail();
        }
        //f2i
        if (max != (int) bigFLT) {
            TestUtil.fail();
        }
        //f2l
        if (maxL != (long) bigFLT) {
            TestUtil.fail();
        }
        int toB;
        if (TestUtil.equalInt(c, c)) {
            toB = 128;
        } else {
            toB = 128;
        }
        //i2b
        byte bt = (byte) toB;
        TestUtil.equalInt(bt, -128);
        //i2c
        char ch = (char) toB;
        TestUtil.equalInt(ch, 128);
        //i2d
        TestUtil.equalInt((int) ((double) toB + 0.0), toB);
        //i2l & l2i
        TestUtil.equalInt((int) ((long) toB + ch + bt), toB);
        //l2d
        TestUtil.equalInt((int) ((double) ((long) toB + ch + bt) + 0.0), toB);
        //l2f
        TestUtil.equalInt((int) ((float) ((long) toB + ch + bt) + 0.0f), toB);
        toB <<= 8;
        //i2s
        short sh = (short) toB;
        TestUtil.equalInt(sh, -toB);
    }

    //Areturn
    public InstructionTest retObject(int a) {
        if (a > 0) {
            return new InstructionTest();
        } else {
            return new InstructionTest();
        }
    }

    //Dreturn
    public double retDouble(int a) {
        if (a > 0) {
            return 3;
        } else {
            return 5;
        }
    }

    //Freturn
    public float retFloat(int a) {
        if (a > 0) {
            return 3;
        } else {
            return 5;
        }
    }

    //Ireturn
    public int retInt(int a) {
        if (a > 0) {
            return 3;
        } else {
            return 5;
        }
    }

    //Lreturn
    public long retLong(int a) {
        if (a > 0) {
            return 3;
        } else {
            return 5;
        }
    }


    public void testReturn() {
        InstructionTest test = retObject(3);
        double v = test.retDouble(3);
        float v1 = retFloat(3);
        int i = retInt(3);
        long l = retLong(3);
        TestUtil.equalInt((int) v, 3);
        TestUtil.equalInt((int) v1, i);
        TestUtil.equalInt(i, (int) l);
        TestUtil.equalInt(i, 3);
    }

    public void testBranch(int small, int big, long smallL, long bigL, float smallF, float bigF,
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
            if (big+1 >= small) {
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
        InstructionTest tester = new InstructionTest();
        int ret = tester.run();
        if (!tester.fail) {
            tester.testAdd();
            ret = tester.result;
        }
        tester.testConversion(3.99f, 3.99, 2147483648.0, 2147483648.0f);
        tester.testReturn();
        tester.testBranch(3,4,5,6,7f,8f,9,10);
    }

}
