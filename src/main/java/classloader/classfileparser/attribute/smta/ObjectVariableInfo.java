package classloader.classfileparser.attribute.smta;

import classloader.classfileparser.BuildUtil;

public class ObjectVariableInfo extends VerificationTypeInfo {
    private int cpIndex;

    public ObjectVariableInfo(BuildUtil buildUtil) {
        super(VerificationTypeInfo.ITEM_Object);
        cpIndex = buildUtil.getU2();
    }
}
