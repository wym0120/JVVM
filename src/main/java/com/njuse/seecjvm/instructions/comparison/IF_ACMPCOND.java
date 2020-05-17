package com.njuse.seecjvm.instructions.comparison;

import com.njuse.seecjvm.instructions.base.BranchInstruction;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.struct.JObject;

public abstract class IF_ACMPCOND extends BranchInstruction {
    @Override
    public void execute(StackFrame frame) {
        JObject value2 = frame.getOperandStack().popObjectRef();
        JObject value1 = frame.getOperandStack().popObjectRef();

        if (condition(value1, value2)) {
            int nextPC = frame.getNextPC();
            int branchNext = nextPC - 3 + offset; // 3 = opcode + signed short offset
            frame.setNextPC(branchNext);
        }
    }

    protected abstract boolean condition(JObject v1, JObject v2);
}
