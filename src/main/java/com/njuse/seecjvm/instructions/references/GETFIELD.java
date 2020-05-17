package com.njuse.seecjvm.instructions.references;

import com.njuse.seecjvm.instructions.base.Index16Instruction;
import com.njuse.seecjvm.memory.jclass.Field;
import com.njuse.seecjvm.memory.jclass.JClass;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.ref.FieldRef;
import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.Vars;
import com.njuse.seecjvm.runtime.struct.NonArrayObject;

public class GETFIELD extends Index16Instruction {
    @Override
    public void execute(StackFrame frame) {
        RuntimeConstantPool currentRuntimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        FieldRef fieldRef = (FieldRef) currentRuntimeConstantPool.getConstant(index);
        Field field;
        try {
            field = fieldRef.getResolvedFieldRef();
            JClass targetClazz = field.getClazz();
            if (field.isStatic()) {
                throw new IncompatibleClassChangeError();
            }
            OperandStack stack = frame.getOperandStack();
            NonArrayObject ref = (NonArrayObject) stack.popObjectRef();
            //check null pointer exception
            if (ref.isNull()) {
                throw new NullPointerException();
            }
            String descriptor = field.getDescriptor();
            int slotID = field.getSlotID();
            Vars fields = ref.getFields();
            switch (descriptor.charAt(0)) {
                case 'Z':
                case 'B':
                case 'C':
                case 'S':
                case 'I':
                    stack.pushInt(fields.getInt(slotID));
                    break;
                case 'F':
                    stack.pushFloat(fields.getFloat(slotID));
                    break;
                case 'J':
                    stack.pushLong(fields.getLong(slotID));
                    break;
                case 'D':
                    stack.pushDouble(fields.getDouble(slotID));
                    break;
                case 'L':
                case '[':
                    stack.pushObjectRef(fields.getObjectRef(slotID));
                    break;
                default:
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
