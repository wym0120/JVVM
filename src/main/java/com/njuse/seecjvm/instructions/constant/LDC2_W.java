package com.njuse.seecjvm.instructions.constant;

import com.njuse.seecjvm.instructions.base.Index16Instruction;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.Constant;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.wrapper.DoubleWrapper;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.wrapper.LongWrapper;
import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;

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
