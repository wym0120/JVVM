package com.njuse.seecjvm.instructions.references;

import com.njuse.seecjvm.instructions.base.Index16Instruction;
import com.njuse.seecjvm.memory.JHeap;
import com.njuse.seecjvm.memory.jclass.JClass;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.ref.ClassRef;
import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.struct.ArrayObject;

public class ANEWARRAY extends Index16Instruction {
    @Override
    public void execute(StackFrame frame) {
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        ClassRef classRef = (ClassRef) runtimeConstantPool.getConstant(index);
        try {
            JClass componentClass = classRef.getResolvedClass();
            OperandStack stack = frame.getOperandStack();
            int len = stack.popInt();
            if (len < 0) throw new NegativeArraySizeException();
            JClass arrClass = componentClass.getArrayClass();
            ArrayObject ref = arrClass.newArrayObject(len);
            //add to heap
            JHeap.getInstance().addObj(ref);
            stack.pushObjectRef(ref);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
