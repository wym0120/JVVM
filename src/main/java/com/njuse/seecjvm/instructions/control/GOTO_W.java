package com.njuse.seecjvm.instructions.control;

import com.njuse.seecjvm.instructions.base.BranchInstruction;
import com.njuse.seecjvm.runtime.StackFrame;

import java.nio.ByteBuffer;

public class GOTO_W extends BranchInstruction {
    public void fetchOperands(ByteBuffer reader) {
        offset = reader.getInt();
    }

    @Override
    public void execute(StackFrame frame) {
        int branchPC = frame.getNextPC() - 3 + super.offset;// 3 = opcode + signed short offset
        frame.setNextPC(branchPC);
    }
}
