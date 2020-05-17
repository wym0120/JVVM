package com.njuse.seecjvm.instructions.comparison;

import com.njuse.seecjvm.instructions.base.BranchInstruction;
import com.njuse.seecjvm.runtime.StackFrame;

public abstract class IFCOND extends BranchInstruction {
    @Override
    public void execute(StackFrame frame) {
        int value = frame.getOperandStack().popInt();
        if (condition(value)) {
            int nextPC = frame.getNextPC();
            int branchPC = nextPC - 3 + super.offset; // 3 = opcode + signed short offset
            frame.setNextPC(branchPC);
        }
    }

    protected abstract boolean condition(int value);

}
