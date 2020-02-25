package instructions.math.algorithm;

import instructions.base.Instruction;
import runtime.StackFrame;
import runtime.Vars;

import java.nio.ByteBuffer;

public class IINC extends Instruction {
    private int index;
    private int num;

    @Override
    public void execute(StackFrame frame) {
        Vars vars = frame.getLocalVars();
        int val = vars.getInt(index);
        vars.setInt(index, val + num);
    }

    @Override
    public void fetchOperands(ByteBuffer reader) {
        index = (int) reader.get() & 0xF;
        num = reader.get();
    }

}
