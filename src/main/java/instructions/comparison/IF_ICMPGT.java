package instructions.comparison;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-03-10
 */
public class IF_ICMPGT extends IF_ICMPCOND {
    @Override
    protected boolean condition(int v1, int v2) {
        return v1 > v2;
    }
}
