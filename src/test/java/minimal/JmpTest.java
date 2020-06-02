package minimal;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-05-31
 */
public class JmpTest {

    public static void testJmp(boolean a, boolean b, boolean c) {
        //ifeq
        if (a) {
            TestUtil.fail();
        } else {
            //ifne ifeq
            if (b || c) {
                //ifeq
                if (c) {
                    //goto
                    //return
                }else {
                    TestUtil.fail();
                }
            } else {
                TestUtil.fail();
            }
        }

    }


    public static void main(String[] args) {
        testJmp(false, false, true);
    }
}
