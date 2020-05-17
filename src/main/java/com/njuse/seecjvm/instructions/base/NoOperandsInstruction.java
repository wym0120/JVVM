package com.njuse.seecjvm.instructions.base;

import java.nio.ByteBuffer;

public abstract class NoOperandsInstruction extends Instruction {
    public void fetchOperands(ByteBuffer reader) {
        //do nothing
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}
