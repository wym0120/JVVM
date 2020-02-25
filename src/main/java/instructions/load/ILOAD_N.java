package instructions.load;

import runtime.StackFrame;

public class ILOAD_N extends LOAD_N {
    public ILOAD_N(int index){
        checkIndex(index);
        this.index = index;
    }

    @Override
    public void execute(StackFrame frame) {
        int val = frame.getLocalVars().getInt(index);
        frame.getOperandStack().pushInt(val);
    }
}
