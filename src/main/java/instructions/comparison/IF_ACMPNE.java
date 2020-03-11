package instructions.comparison;

import runtime.struct.JObject;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-03-10
 */
public class IF_ACMPNE extends IF_ACMPCOND {

    @Override
    protected boolean condition(JObject v1, JObject v2) {
        return !v1.equals(v2);
    }
}
