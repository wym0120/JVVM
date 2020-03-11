package instructions.base;

import lombok.Data;

import java.nio.ByteBuffer;

public abstract class BranchInstruction extends Instruction{
    protected int offset;

    public void fetchOperands(ByteBuffer reader){
        offset = reader.getShort();
    }
}
