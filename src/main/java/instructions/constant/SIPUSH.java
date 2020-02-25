package instructions.constant;

import instructions.base.Instruction;
import runtime.StackFrame;

import java.nio.ByteBuffer;

public class SIPUSH extends Instruction {
    private short val;
    @Override
    public void fetchOperands(ByteBuffer reader) {
        val = reader.getShort();
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushInt(val);
    }
}
