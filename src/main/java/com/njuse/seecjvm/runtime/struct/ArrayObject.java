package com.njuse.seecjvm.runtime.struct;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArrayObject extends JObject {
    protected int len;
    protected String type;

    public ArrayObject(int len, String type) {
        if (len < 0) throw new NegativeArraySizeException();
        this.len = len;
        this.type = type;
        numInHeap++;
    }
}
