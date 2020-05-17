package com.njuse.seecjvm.instructions.math.shift;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;

public class LUSHR extends NoOperandsInstruction {

    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int val2 = stack.popInt();
        long val1 = stack.popLong();
        long res = val1 >>> (val2 & 0x2f);
        stack.pushLong(res);
    }
}
