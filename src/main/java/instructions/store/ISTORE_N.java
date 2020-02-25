package instructions.store;

import runtime.StackFrame;

public class ISTORE_N extends STORE_N{
    public ISTORE_N(int index){
        checkIndex(index);
        this.index = index;
    }

    @Override
    public void execute(StackFrame frame) {
        int val = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(index,val);
    }
}
