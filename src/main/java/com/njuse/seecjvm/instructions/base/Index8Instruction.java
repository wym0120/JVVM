package com.njuse.seecjvm.instructions.base;

import java.nio.ByteBuffer;

public abstract class Index8Instruction extends Instruction {
    public int index;//type of index is unsigned char

    public void fetchOperands(ByteBuffer reader) {
        index = (int) reader.get() & 0xFF;
    }

    public String toString() {
        return this.getClass().getSimpleName() + " index: " + (index & 0XFF);
    }
}
