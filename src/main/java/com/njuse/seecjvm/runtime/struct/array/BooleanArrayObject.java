package com.njuse.seecjvm.runtime.struct.array;

import com.njuse.seecjvm.runtime.struct.ArrayObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BooleanArrayObject extends ArrayObject {
    private boolean[] array;

    public BooleanArrayObject(int len, String type) {
        super(len, type);
        array = new boolean[len];
    }
}