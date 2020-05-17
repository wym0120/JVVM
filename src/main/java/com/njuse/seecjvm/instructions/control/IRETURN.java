package com.njuse.seecjvm.instructions.control;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.JThread;
import com.njuse.seecjvm.runtime.StackFrame;

public class IRETURN extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        int value = frame.getOperandStack().popInt();
        JThread thread = frame.getThread();
        thread.popFrame();
        thread.getTopFrame().getOperandStack().pushInt(value);
    }
}
