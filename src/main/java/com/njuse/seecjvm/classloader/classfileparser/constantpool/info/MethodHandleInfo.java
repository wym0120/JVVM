package com.njuse.seecjvm.classloader.classfileparser.constantpool.info;

import com.njuse.seecjvm.classloader.classfileparser.constantpool.ConstantPool;

public class MethodHandleInfo extends ConstantPoolInfo {
    private int referenceKind;
    private int referenceIndex;

    public MethodHandleInfo(ConstantPool constantPool, int referenceKind, int referenceIndex) {
        super(constantPool);
        this.referenceKind = referenceKind;
        this.referenceIndex = referenceIndex;
        super.tag = ConstantPoolInfo.METHOD_HANDLE;
    }
}
