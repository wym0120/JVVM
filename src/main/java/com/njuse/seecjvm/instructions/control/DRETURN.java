package com.njuse.seecjvm.instructions.control;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.JThread;
import com.njuse.seecjvm.runtime.StackFrame;

public class DRETURN extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        double value = frame.getOperandStack().popDouble();
        JThread thread = frame.getThread();
        thread.popFrame();
        thread.getTopFrame().getOperandStack().pushDouble(value);
    }
}
