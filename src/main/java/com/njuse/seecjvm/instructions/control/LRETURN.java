package com.njuse.seecjvm.instructions.control;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.JThread;
import com.njuse.seecjvm.runtime.StackFrame;

public class LRETURN extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        long value = frame.getOperandStack().popLong();
        JThread thread = frame.getThread();
        thread.popFrame();
        thread.getTopFrame().getOperandStack().pushLong(value);
    }
}
