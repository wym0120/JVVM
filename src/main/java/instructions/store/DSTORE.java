package instructions.store;

import instructions.base.Index8Instruction;
import runtime.StackFrame;

public class DSTORE extends Index8Instruction {
    @Override
    public void execute(StackFrame frame) {
        double val = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(index,val);
    }
}
