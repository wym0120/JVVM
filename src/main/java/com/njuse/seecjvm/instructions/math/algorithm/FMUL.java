package com.njuse.seecjvm.instructions.math.algorithm;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;

public class FMUL extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        float val2 = stack.popFloat();
        float val1 = stack.popFloat();
        float res = val1 * val2;
        stack.pushFloat(res);
    }
}
