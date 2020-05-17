package instructions.comparison;

import instructions.base.NoOperandsInstruction;
import runtime.StackFrame;

public class DCMPG extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        double value2 = frame.getOperandStack().popDouble();
        double value1 = frame.getOperandStack().popDouble();
        if (Double.isNaN(value1) || Double.isNaN(value2)) {
            frame.getOperandStack().pushInt(1);
        } else {
            frame.getOperandStack().pushInt(Double.compare(value1, value2));
        }

    }
}
