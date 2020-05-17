package com.njuse.seecjvm.instructions.load;

import com.njuse.seecjvm.instructions.base.Index8Instruction;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.struct.JObject;

public class ALOAD extends Index8Instruction {
    @Override
    public void execute(StackFrame frame) {
        JObject ref = frame.getLocalVars().getObjectRef(index);
        frame.getOperandStack().pushObjectRef(ref);
    }
}
