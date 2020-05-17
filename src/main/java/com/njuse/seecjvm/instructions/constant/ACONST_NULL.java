package com.njuse.seecjvm.instructions.constant;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.struct.NullObject;

public class ACONST_NULL extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushObjectRef(new NullObject());
    }
}
