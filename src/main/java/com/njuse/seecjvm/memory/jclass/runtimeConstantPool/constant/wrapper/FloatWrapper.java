package com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.wrapper;

import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.Constant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FloatWrapper implements Constant {
    private float value;

    public FloatWrapper(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "float " + value;
    }
}
