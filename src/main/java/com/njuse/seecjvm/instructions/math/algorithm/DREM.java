package com.njuse.seecjvm.instructions.math.algorithm;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;

public class DREM extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        double val2 = stack.popDouble();
        double val1 = stack.popDouble();
        double res = val2 % val1;
        stack.pushDouble(res);
    }
}
