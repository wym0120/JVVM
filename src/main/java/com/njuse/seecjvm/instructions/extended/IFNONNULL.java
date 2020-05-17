package com.njuse.seecjvm.instructions.extended;

import com.njuse.seecjvm.instructions.base.BranchInstruction;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.struct.JObject;

public class IFNONNULL extends BranchInstruction {
    @Override
    public void execute(StackFrame frame) {
        JObject ref = frame.getOperandStack().popObjectRef();
        if (!ref.isNull()) {
            int branchPC = frame.getNextPC() - 3 + super.offset;
            frame.setNextPC(branchPC);
        }
    }
}
