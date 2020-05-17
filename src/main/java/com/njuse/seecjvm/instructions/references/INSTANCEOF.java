package com.njuse.seecjvm.instructions.references;

import com.njuse.seecjvm.instructions.base.Index16Instruction;
import com.njuse.seecjvm.memory.jclass.JClass;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.ref.ClassRef;
import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.struct.JObject;

public class INSTANCEOF extends Index16Instruction {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        JObject ref = stack.popObjectRef();
        if (ref.isNull()) {
            stack.pushInt(0);
            return;
        }

        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        ClassRef classRef = (ClassRef) runtimeConstantPool.getConstant(index);
        try {
            JClass clazz = classRef.getResolvedClass();
            if (ref.isInstanceOf(clazz)) {
                stack.pushInt(1);
            } else {
                stack.pushInt(0);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
