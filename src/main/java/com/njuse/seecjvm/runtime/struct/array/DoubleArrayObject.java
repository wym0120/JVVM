package com.njuse.seecjvm.runtime.struct.array;

import com.njuse.seecjvm.runtime.struct.ArrayObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoubleArrayObject extends ArrayObject {
    private double[] array;

    public DoubleArrayObject(int len, String type) {
        super(len, type);
        array = new double[len];
    }
}