package com.njuse.seecjvm.instructions.math.shift;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;

public class ISHL extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int val2 = stack.popInt();
        int val1 = stack.popInt();
        int res = val1 << (val2 & 0x1f);
        stack.pushInt(res);
    }
}
