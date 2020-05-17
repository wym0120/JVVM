package com.njuse.seecjvm.runtime.struct.array;

import com.njuse.seecjvm.runtime.struct.ArrayObject;
import com.njuse.seecjvm.runtime.struct.JObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefArrayObject extends ArrayObject {
    private JObject[] array;

    public RefArrayObject(int len, String type) {
        super(len, type);
        array = new JObject[len];

    }
}
