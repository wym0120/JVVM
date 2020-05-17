package com.njuse.seecjvm.instructions.control;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.JThread;
import com.njuse.seecjvm.runtime.StackFrame;

public class RETURN extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        JThread thread = frame.getThread();
        thread.popFrame();
    }
}
