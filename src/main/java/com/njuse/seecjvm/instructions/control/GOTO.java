package com.njuse.seecjvm.instructions.control;

import com.njuse.seecjvm.instructions.base.BranchInstruction;
import com.njuse.seecjvm.runtime.StackFrame;

public class GOTO extends BranchInstruction {
    @Override
    public void execute(StackFrame frame) {
        int branchPC = frame.getNextPC() - 3 + super.offset;// 3 = opcode + signed short offset
        frame.setNextPC(branchPC);
    }
}
