package instructions.comparison;

import instructions.base.NoOperandsInstruction;
import runtime.StackFrame;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-03-08
 */
public class FCMPG extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        float value2 = frame.getOperandStack().popFloat();
        float value1 = frame.getOperandStack().popFloat();
        if (Float.isNaN(value1) || Float.isNaN(value2)) {
            frame.getOperandStack().pushInt(1);
        } else {
            frame.getOperandStack().pushInt(Float.compare(value1, value2));
        }

    }
}
