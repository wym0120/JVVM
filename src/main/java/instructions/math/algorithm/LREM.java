package instructions.math.algorithm;

import instructions.base.NoOperandsInstruction;
import runtime.OperandStack;
import runtime.StackFrame;

public class LREM extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        long val2 = stack.popLong();
        long val1 = stack.popLong();
        if (val2 == 0) throw new ArithmeticException();
        long res = val1 % val2;
        stack.pushLong(res);
    }
}
