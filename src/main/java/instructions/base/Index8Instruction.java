package instructions.base;

import java.nio.ByteBuffer;

/**
 * instructions get operands from localVars
 * @author WYM
 */
public abstract class Index8Instruction extends Instruction{
    int index;//byte needs to be cast to unsigned int

    public void fetchOperands(ByteBuffer reader){

    }
}
