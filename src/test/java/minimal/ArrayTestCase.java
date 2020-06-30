package minimal;

/**
 * Description:
 *
 * @author xxz
 * Created on 06/30/2020
 */
public class ArrayTestCase {

    public void runTest(int[] array){
        array[3] = array[2] + array[1];
    }


    public static void main(String[] args) {
        int[] array = {
                2, 3, 4, 5, 6, 7,
        };
        ArrayTestCase test = new ArrayTestCase();
        test.runTest(array);
        TestUtil.equalInt(array[2] + array[1], array[3]);
    }
}
