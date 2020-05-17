package com.njuse.seecjvm.instructions.math.algorithm;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;

public class DNEG extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        double val = stack.popDouble();
        val = -val;
        stack.pushDouble(val);
    }
}
