package instructions.base;

import lombok.Data;

import java.nio.ByteBuffer;

public abstract class BranchInstruction extends Instruction{
    protected int offset;//short value needs to be cast to unsigned int

    public void fetchOperands(ByteBuffer reader){
        offset = (int) reader.getShort() & 0xFFFF;
    }
}
