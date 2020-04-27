package instructions.constant;

import instructions.base.Instruction;
import runtime.StackFrame;

import java.nio.ByteBuffer;

public class BIPUSH extends Instruction {
    private byte val;

    @Override
    public void fetchOperands(ByteBuffer reader) {
        val = reader.get();
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushInt(val);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " value : " + val;
    }
}
