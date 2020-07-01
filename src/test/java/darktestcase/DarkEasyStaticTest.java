package darktestcase;

import minimal.TestUtil;

/**
 * Description:
 *
 * @author xxz
 * Created on 07/01/2020
 */
public class DarkEasyStaticTest {
    private static int a = 3;
    static {
        TestUtil.reach(2333);
    }

    public static void main(String[] args) {
        TestUtil.reach(1234);
        TestUtil.equalInt(3, a);
    }
}
