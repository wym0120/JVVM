package instructions.base;

import java.nio.ByteBuffer;

public abstract class BranchInstruction extends Instruction {
    protected int offset;

    public void fetchOperands(ByteBuffer reader) {
        offset = reader.getShort();
    }

    public String toString() {
        return this.getClass().getSimpleName() + " offset: " + offset;
    }
}
