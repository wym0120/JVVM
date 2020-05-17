package com.njuse.seecjvm.instructions.control;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.JThread;
import com.njuse.seecjvm.runtime.StackFrame;

public class FRETURN extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        float value = frame.getOperandStack().popFloat();
        JThread thread = frame.getThread();
        thread.popFrame();
        thread.getTopFrame().getOperandStack().pushFloat(value);
    }
}
