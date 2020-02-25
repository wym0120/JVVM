package instructions.load;

import instructions.base.NoOperandsInstruction;
import runtime.StackFrame;

import java.util.Arrays;

public class DLOAD_N extends LOAD_N {
    public DLOAD_N(int index){
        checkIndex(index);
        this.index = index;
    }

    @Override
    public void execute(StackFrame frame) {
        double val = frame.getLocalVars().getDouble(index);
        frame.getOperandStack().pushDouble(val);
    }
}
