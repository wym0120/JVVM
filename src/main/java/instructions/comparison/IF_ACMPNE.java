package instructions.comparison;

import runtime.struct.JObject;

public class IF_ACMPNE extends IF_ACMPCOND {

    @Override
    protected boolean condition(JObject v1, JObject v2) {
        return !v1.equals(v2);
    }
}
