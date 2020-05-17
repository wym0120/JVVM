package com.njuse.seecjvm.instructions.references;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.struct.ArrayObject;
import com.njuse.seecjvm.runtime.struct.JObject;

public class ARRAYLENGTH extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        JObject ref = stack.popObjectRef();
        if (ref == null || ref.isNull()) throw new NullPointerException();
        stack.pushInt(((ArrayObject) ref).getLen());
    }
}
