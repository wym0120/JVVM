package classloader.attribute.smta;

import classloader.BuildInfo;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-18
 */
public class ObjectVariableInfo extends VerificationTypeInfo {
    private int cpIndex;

    public ObjectVariableInfo(BuildInfo buildInfo) {
        super(VerificationTypeInfo.ITEM_Object);
        cpIndex = buildInfo.getU2();
    }
}
