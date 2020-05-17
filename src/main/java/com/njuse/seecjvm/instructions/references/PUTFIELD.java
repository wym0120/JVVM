package com.njuse.seecjvm.instructions.references;

import com.njuse.seecjvm.instructions.base.Index16Instruction;
import com.njuse.seecjvm.memory.jclass.Field;
import com.njuse.seecjvm.memory.jclass.JClass;
import com.njuse.seecjvm.memory.jclass.Method;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.ref.FieldRef;
import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.struct.JObject;
import com.njuse.seecjvm.runtime.struct.NonArrayObject;


public class PUTFIELD extends Index16Instruction {
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
            if (field.isStatic()) {
                throw new IncompatibleClassChangeError();
            }
            if (field.isFinal()) {
                if (field.getClazz() != currentClazz || !currentMethod.getName().equals("<init>")) {
                    throw new IllegalAccessError();
                }
            }
            String descriptor = field.getDescriptor();
            int slotID = field.getSlotID();
            OperandStack stack = frame.getOperandStack();
            JObject ref;
            switch (descriptor.charAt(0)) {
                case 'Z':
                case 'B':
                case 'C':
                case 'S':
                case 'I':
                    int intVal = stack.popInt();
                    ref = stack.popObjectRef();
                    checkRef(ref);
                    ((NonArrayObject) ref).getFields().setInt(slotID, intVal);
                    break;
                case 'F':
                    float floatVal = stack.popFloat();
                    ref = stack.popObjectRef();
                    checkRef(ref);
                    ((NonArrayObject) ref).getFields().setFloat(slotID, floatVal);
                    break;
                case 'J':
                    long longVal = stack.popLong();
                    ref = stack.popObjectRef();
                    checkRef(ref);
                    ((NonArrayObject) ref).getFields().setLong(slotID, longVal);
                    break;
                case 'D':
                    double doubleVal = stack.popDouble();
                    ref = stack.popObjectRef();
                    checkRef(ref);
                    ((NonArrayObject) ref).getFields().setDouble(slotID, doubleVal);
                    break;
                case '[':
                case 'L':
                    JObject refVal = stack.popObjectRef();
                    ref = stack.popObjectRef();
                    ((NonArrayObject) ref).getFields().setObjectRef(slotID, refVal);
                    break;
                default:

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void checkRef(JObject obj) {
        assert obj instanceof NonArrayObject;
        if (obj.isNull()) throw new NullPointerException();
    }
}
