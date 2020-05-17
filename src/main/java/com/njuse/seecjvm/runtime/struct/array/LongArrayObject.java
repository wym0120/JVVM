package com.njuse.seecjvm.runtime.struct.array;

import com.njuse.seecjvm.runtime.struct.ArrayObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LongArrayObject extends ArrayObject {
    private long[] array;

    public LongArrayObject(int len, String type) {
        super(len, type);
        array = new long[len];
    }
}