package com.njuse.seecjvm.runtime.struct.array;

import com.njuse.seecjvm.runtime.struct.ArrayObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ByteArrayObject extends ArrayObject {
    private byte[] array;

    public ByteArrayObject(int len, String type) {
        super(len, type);
        array = new byte[len];
    }
}
