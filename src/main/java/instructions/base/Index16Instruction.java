package instructions.base;

import java.nio.ByteBuffer;

/**
 * Instructions get operands from runtime constant pool
 */
public abstract class Index16Instruction extends Instruction{
    int index; //short value needs to be cast to unsigned int

    public void fetchOperands(ByteBuffer reader){

    }
}
