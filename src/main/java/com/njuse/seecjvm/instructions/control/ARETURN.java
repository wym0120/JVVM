package com.njuse.seecjvm.instructions.control;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.JThread;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.struct.JObject;

public class ARETURN extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        JObject ref = frame.getOperandStack().popObjectRef();
        JThread thread = frame.getThread();
        thread.popFrame();
        thread.getTopFrame().getOperandStack().pushObjectRef(ref);
    }
}
