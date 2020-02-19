package classloader.attribute.smta;

import classloader.BuildInfo;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-18
 */
public class UninitializedVariableInfo extends VerificationTypeInfo {
    private int offset;
    public UninitializedVariableInfo(BuildInfo buildInfo) {
        super(VerificationTypeInfo.ITEM_Uninitialized);
        this.offset = buildInfo.getU2();
    }
}
