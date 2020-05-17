package com.njuse.seecjvm.classloader.classfileparser.constantpool.info;

import com.njuse.seecjvm.classloader.classfileparser.constantpool.ConstantPool;

public class InvokeDynamicInfo extends ConstantPoolInfo {
    private int bootstrapMethodAttrIndex;
    private int nameAndTypeIndex;

    public InvokeDynamicInfo(ConstantPool constantPool, int bootstrapMethodAttrIndex, int nameAndTypeIndex) {
        super(constantPool);
        this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
        super.tag = ConstantPoolInfo.INVOKE_DYNAMIC;
    }
}
