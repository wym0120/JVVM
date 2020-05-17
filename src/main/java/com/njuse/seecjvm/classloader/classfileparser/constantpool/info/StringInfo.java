package com.njuse.seecjvm.classloader.classfileparser.constantpool.info;

import com.njuse.seecjvm.classloader.classfileparser.constantpool.ConstantPool;

public class StringInfo extends ConstantPoolInfo {
    private int stringIndex;

    public StringInfo(ConstantPool constantPool, int stringIndex) {
        super(constantPool);
        this.stringIndex = stringIndex;
        super.tag = ConstantPoolInfo.STRING;
    }

    public String getStringValue() {
        UTF8Info utf8Info = (UTF8Info) myCP.get(stringIndex);
        return utf8Info.getString();
    }
}
