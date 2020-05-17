package com.njuse.seecjvm.instructions.references;

import com.njuse.seecjvm.instructions.base.Index16Instruction;
import com.njuse.seecjvm.memory.jclass.Field;
import com.njuse.seecjvm.memory.jclass.InitState;
import com.njuse.seecjvm.memory.jclass.JClass;
import com.njuse.seecjvm.memory.jclass.Method;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.ref.FieldRef;
import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.Vars;

public class PUTSTATIC extends Index16Instruction {
    @Override
    public void execute(StackFrame frame) {
        Method currentMethod = frame.getMethod();
        JClass currentClazz = currentMethod.getClazz();
        RuntimeConstantPool currentRuntimeConstantPool = currentClazz.getRuntimeConstantPool();
        FieldRef fieldRef = (FieldRef) currentRuntimeConstantPool.getConstant(index);
        Field field;
        try {
            field = fieldRef.getResolvedFieldRef();
            JClass targetClazz = field.getClazz();

            //check class whether init
            if (targetClazz.getInitState() == InitState.PREPARED) {
                frame.setNextPC(frame.getNextPC() - 3);//opcode + operand = 3bytes
                targetClazz.initClass(frame.getThread(), targetClazz);
                return;
            }

            if (!field.isStatic()) {
                throw new IncompatibleClassChangeError();
            }
            if (field.isFinal()) {
                if (field.getClazz() != currentClazz || !currentMethod.getName().equals("<clinit>")) {
                    throw new IllegalAccessError();
                }
            }
            String descriptor = field.getDescriptor();
            int slotID = field.getSlotID();
            Vars staticVars = targetClazz.getStaticVars();
            OperandStack stack = frame.getOperandStack();
            switch (descriptor.charAt(0)) {
                case 'Z':
                case 'B':
                case 'C':
                case 'S':
                case 'I':
                    staticVars.setInt(slotID, stack.popInt());
                    break;
                case 'F':
                    staticVars.setFloat(slotID, stack.popFloat());
                    break;
                case 'J':
                    staticVars.setLong(slotID, stack.popLong());
                    break;
                case 'D':
                    staticVars.setDouble(slotID, stack.popDouble());
                    break;
                case 'L':
                case '[':
                    staticVars.setObjectRef(slotID, stack.popObjectRef());
                    break;
                default:
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
