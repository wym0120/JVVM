package instructions.math.shift;

import instructions.base.NoOperandsInstruction;
import runtime.OperandStack;
import runtime.StackFrame;

public class LSHL extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int val2 = stack.popInt();
        long val1 = stack.popLong();
        long res = val1 << (val2 & 0x1f);
        stack.pushLong(res);
    }
}
