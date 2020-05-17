package com.njuse.seecjvm.instructions.base;

import java.nio.ByteBuffer;

public abstract class Index16Instruction extends Instruction {
    public int index; //type of index is unsigned short

    public void fetchOperands(ByteBuffer reader) {
        index = (int) reader.getShort() & 0xFFFF;
    }

    public String toString() {
        return this.getClass().getSimpleName() + " index: " + (index & 0xFFFF);
    }
}
