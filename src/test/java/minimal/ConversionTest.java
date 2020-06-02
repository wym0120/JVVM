package minimal;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-06-01
 */
public class ConversionTest {
    public static void test(float flt, double db, double bigDB, float bigFLT) {
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

    public static void main(String[] args) {
        test(3.99f, 3.99, 2147483648.0, 2147483648.0f);
    }
}
