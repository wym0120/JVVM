package classloader.classfileparser.attribute.smta;

import classloader.classfileparser.BuildUtil;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-18
 */
public class ObjectVariableInfo extends VerificationTypeInfo {
    private int cpIndex;

    public ObjectVariableInfo(BuildUtil buildUtil) {
        super(VerificationTypeInfo.ITEM_Object);
        cpIndex = buildUtil.getU2();
    }
}
