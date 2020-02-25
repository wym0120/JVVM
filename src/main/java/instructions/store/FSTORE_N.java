package instructions.store;

import runtime.StackFrame;

public class FSTORE_N extends STORE_N{
    public FSTORE_N(int index){
        checkIndex(index);
        this.index = index;
    }

    @Override
    public void execute(StackFrame frame) {
        float val = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(index,val);
    }
}
