package com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.other;

import com.njuse.seecjvm.classloader.classfileparser.constantpool.info.NameAndTypeInfo;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.Constant;

public class NameAndType implements Constant {
    private int nameIndex;
    private int descriptorIndex;

    public NameAndType(NameAndTypeInfo info) {
        this.nameIndex = info.getNameIndex();
        this.descriptorIndex = info.getDescriptorIndex();
    }

    @Override
    public String toString() {
        return "NameAndType : nameIndex = " + nameIndex + " descriptorIndex = " + descriptorIndex;
    }
}
