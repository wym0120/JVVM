package com.njuse.seecjvm.classloader.classfileparser.attribute;

import com.njuse.seecjvm.classloader.classfileparser.BuildUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantValueAttr extends AttributeInfo {
    private int constantValueIndex;

    public ConstantValueAttr(BuildUtil buildUtil, int index, int length) {
        super(index, length);
        constantValueIndex = buildUtil.getU2();
    }
}
