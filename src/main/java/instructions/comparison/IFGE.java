package instructions.comparison;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-03-10
 */
public class IFGE extends IFCOND {
    @Override
    public boolean condition(int value) {
        return value >= 0;
    }
}
