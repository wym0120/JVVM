package com.njuse.seecjvm.instructions.references;

import com.njuse.seecjvm.instructions.base.Index16Instruction;
import com.njuse.seecjvm.memory.JHeap;
import com.njuse.seecjvm.memory.jclass.InitState;
import com.njuse.seecjvm.memory.jclass.JClass;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.ref.ClassRef;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.struct.NonArrayObject;

public class NEW extends Index16Instruction {
    @Override
    public void execute(StackFrame frame) {
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        ClassRef classRef = (ClassRef) runtimeConstantPool.getConstant(index);
        try {
            JClass clazz = classRef.getResolvedClass();

            //check class whether init
            if (clazz.getInitState() == InitState.PREPARED) {
                frame.setNextPC(frame.getNextPC() - 3);//opcode + operand = 3bytes
                clazz.initClass(frame.getThread(), clazz);
                return;
            }

            if (clazz.isInterface() || clazz.isAbstract()) {
                throw new InstantiationError();
            }
            NonArrayObject ref = clazz.newObject();
            //add to heap
            JHeap.getInstance().addObj(ref);
            frame.getOperandStack().pushObjectRef(ref);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
