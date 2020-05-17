package com.njuse.seecjvm.runtime.struct.array;

import com.njuse.seecjvm.runtime.struct.ArrayObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FloatArrayObject extends ArrayObject {
    private float[] array;

    public FloatArrayObject(int len, String type) {
        super(len, type);
        array = new float[len];
    }
}
