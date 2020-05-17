package com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.wrapper;

import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.Constant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoubleWrapper implements Constant {
    private double value;

    public DoubleWrapper(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "double " + value;
    }
}
