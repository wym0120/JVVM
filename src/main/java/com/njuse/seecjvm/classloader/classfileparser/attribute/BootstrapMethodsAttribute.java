package com.njuse.seecjvm.classloader.classfileparser.attribute;

import com.njuse.seecjvm.classloader.classfileparser.BuildUtil;

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
