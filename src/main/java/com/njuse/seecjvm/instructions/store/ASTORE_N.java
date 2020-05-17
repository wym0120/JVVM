package com.njuse.seecjvm.instructions.store;

import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.struct.JObject;

public class ASTORE_N extends STORE_N {
    public ASTORE_N(int index) {
        checkIndex(index);
        this.index = index;
    }

    @Override
    public void execute(StackFrame frame) {
        JObject ref = frame.getOperandStack().popObjectRef();
        frame.getLocalVars().setObjectRef(index, ref);
    }
}
