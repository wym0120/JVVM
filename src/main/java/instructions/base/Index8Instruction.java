package instructions.base;

import java.nio.ByteBuffer;

/**
 * instructions get operands from localVars
 *
 * @author WYM
 */
public abstract class Index8Instruction extends Instruction {
    public int index;//byte needs to be cast to unsigned int

    public void fetchOperands(ByteBuffer reader) {
        index = (int) reader.get() & 0xFF;
    }

    public String toString() {
        return this.getClass().getSimpleName() + " index: " + (index & 0XFF);
    }
}
