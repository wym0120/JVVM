package com.njuse.seecjvm.instructions.math.algorithm;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;

public class LREM extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        long val2 = stack.popLong();
        long val1 = stack.popLong();
        if (val2 == 0) throw new ArithmeticException();
        long res = val1 % val2;
        stack.pushLong(res);
    }
}
