package classloader.attribute;

import classloader.BuildUtil;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-19
 */
public class BootstrapMethodsAttribute extends AttributeInfo {
    private int numBootstrapMethods;
    private BootstrapMethodInfo[] bootstrapMethods;

    public BootstrapMethodsAttribute(BuildUtil buildUtil, int index, int length) {
        super(index, length);
        numBootstrapMethods = buildUtil.getU2();
        bootstrapMethods = new BootstrapMethodInfo[numBootstrapMethods];
        for (int i = 0; i < bootstrapMethods.length; i++) {
            bootstrapMethods[i] = new BootstrapMethodInfo(buildUtil);
        }
    }
}
