package com.njuse.seecjvm.instructions.math.bool;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;

public class LXOR extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        long val2 = stack.popLong();
        long val1 = stack.popLong();
        long res = val1 ^ val2;
        stack.pushLong(res);
    }
}
