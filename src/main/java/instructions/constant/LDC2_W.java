package instructions.constant;

import instructions.base.Index16Instruction;
import memory.jclass.runtimeConstantPool.constant.Constant;
import memory.jclass.runtimeConstantPool.constant.wrapper.DoubleWrapper;
import memory.jclass.runtimeConstantPool.constant.wrapper.LongWrapper;
import runtime.OperandStack;
import runtime.StackFrame;

public class LDC2_W extends Index16Instruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        Constant constant = frame.getMethod().getClazz().getRuntimeConstantPool().getConstant(index);
        if (constant instanceof LongWrapper) stack.pushLong(((LongWrapper) constant).getValue());
        else if (constant instanceof DoubleWrapper) stack.pushDouble(((DoubleWrapper) constant).getValue());
        else throw new ClassFormatError();
    }
}
