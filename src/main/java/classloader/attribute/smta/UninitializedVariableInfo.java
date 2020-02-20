package classloader.attribute.smta;

import classloader.BuildUtil;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-18
 */
public class UninitializedVariableInfo extends VerificationTypeInfo {
    private int offset;
    public UninitializedVariableInfo(BuildUtil buildUtil) {
        super(VerificationTypeInfo.ITEM_Uninitialized);
        this.offset = buildUtil.getU2();
    }
}
