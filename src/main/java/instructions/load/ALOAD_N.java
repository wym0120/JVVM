package instructions.load;

import runtime.StackFrame;
import runtime.struct.JObject;

public class ALOAD_N extends LOAD_N{
    public ALOAD_N(int index){
        checkIndex(index);
        this.index = index;
    }
    @Override
    public void execute(StackFrame frame) {
        JObject ref = frame.getLocalVars().getObjectRef(index);
        frame.getOperandStack().pushObjectRef(ref);
    }
}
