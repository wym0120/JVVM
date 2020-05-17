package com.njuse.seecjvm.instructions.comparison;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.StackFrame;

public class FCMPL extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        float value2 = frame.getOperandStack().popFloat();
        float value1 = frame.getOperandStack().popFloat();
        if (Float.isNaN(value1) || Float.isNaN(value2)) {
            frame.getOperandStack().pushInt(-1);
        } else {
            frame.getOperandStack().pushInt(Float.compare(value1, value2));
        }

    }
}
