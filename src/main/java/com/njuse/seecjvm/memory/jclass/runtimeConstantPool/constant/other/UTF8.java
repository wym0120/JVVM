package com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.other;

import com.njuse.seecjvm.classloader.classfileparser.constantpool.info.UTF8Info;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.Constant;

public class UTF8 implements Constant {
    private int length;
    private byte[] bytes;
    private String myString;

    public UTF8(UTF8Info info) {
        this.length = info.getLength();
        this.bytes = info.getBytes();
        this.myString = info.getMyString();
    }

    @Override
    public String toString() {
        return "UTF8 " + myString;
    }
}
