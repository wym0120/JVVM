package classloader.classfileparser.attribute.smta;

import classloader.classfileparser.BuildUtil;

public class UninitializedVariableInfo extends VerificationTypeInfo {
    private int offset;
    public UninitializedVariableInfo(BuildUtil buildUtil) {
        super(VerificationTypeInfo.ITEM_Uninitialized);
        this.offset = buildUtil.getU2();
    }
}
