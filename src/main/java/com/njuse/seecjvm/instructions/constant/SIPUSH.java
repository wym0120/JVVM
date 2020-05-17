package com.njuse.seecjvm.instructions.constant;

import com.njuse.seecjvm.instructions.base.Instruction;
import com.njuse.seecjvm.runtime.StackFrame;

import java.nio.ByteBuffer;

public class SIPUSH extends Instruction {
    private short val;

    @Override
    public void fetchOperands(ByteBuffer reader) {
        val = reader.getShort();
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushInt(val);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " value : " + val;
    }
}
