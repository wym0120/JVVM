package com.njuse.seecjvm.instructions.comparison;

import com.njuse.seecjvm.instructions.base.BranchInstruction;
import com.njuse.seecjvm.runtime.StackFrame;

public abstract class IF_ICMPCOND extends BranchInstruction {
    @Override
    public void execute(StackFrame frame) {
        int value2 = frame.getOperandStack().popInt();
        int value1 = frame.getOperandStack().popInt();
        if (condition(value1, value2)) {
            int nextPC = frame.getNextPC();
            int branchNext = nextPC - 3 + offset;// 3 = opcode + signed short offset
            frame.setNextPC(branchNext);
        }
    }

    protected abstract boolean condition(int v1, int v2);
}
