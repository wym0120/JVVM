package com.njuse.seecjvm.instructions.base;

import java.nio.ByteBuffer;

public abstract class BranchInstruction extends Instruction {
    protected int offset;//type of offset is signed short

    public void fetchOperands(ByteBuffer reader) {
        offset = reader.getShort();
    }

    public String toString() {
        return this.getClass().getSimpleName() + " offset: " + offset;
    }
}
