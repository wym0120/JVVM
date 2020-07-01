package minimal;

/**
 * Description:
 *
 * @author xxz
 * Created on 07/01/2020
 */
public class DarkEasyTestUtilTest {
    public static void testTestUtil(){
        TestUtil.equalInt(1, 1);
        TestUtil.equalInt(2, 2);
        TestUtil.reach(2);
        TestUtil.reach(3);
        TestUtil.reach(3);
        TestUtil.reach(3);
        TestUtil.equalInt(3, 4);
        TestUtil.reach(2);
        TestUtil.reach(3);
        TestUtil.reach(3);
    }


    public static void main(String[] args) {
        //expect 2 3 3 3
        //catch 3!=4
        testTestUtil();
    }
}
