package instructions.references;

import instructions.base.Index16Instruction;
import memory.jclass.Field;
import memory.jclass.JClass;
import memory.jclass.Method;
import memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import memory.jclass.runtimeConstantPool.constant.ref.FieldRef;
import runtime.OperandStack;
import runtime.StackFrame;
import runtime.Vars;
import runtime.struct.JObject;

public class PUTFIELD extends Index16Instruction {
    @Override
    public void execute(StackFrame frame) {
        Method currentMethod = frame.getMethod();
        JClass currentClazz = currentMethod.getClazz();
        RuntimeConstantPool currentRuntimeConstantPool = currentClazz.getRuntimeConstantPool();
        FieldRef fieldRef = (FieldRef) currentRuntimeConstantPool.getConstant(index);
        Field field = null;
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
            JObject ref = null;
            switch (descriptor.charAt(0)) {
                case 'Z':
                case 'B':
                case 'C':
                case 'S':
                case 'I':
                    int intVal = stack.popInt();
                    ref = stack.popObjectRef();
                    //todo:nullpointerException need to be check whether set JObject isNull field can trigger this exception!!!!!!
                    if (ref.isNull()) {
                        throw new NullPointerException();
                    }
                    ref.getFields().setInt(slotID, intVal);
                    break;
                case 'F':
                    float floatVal = stack.popFloat();
                    ref = stack.popObjectRef();
                    if (ref.isNull()) {
                        throw new NullPointerException();
                    }
                    ref.getFields().setFloat(slotID, floatVal);
                    break;
                case 'J':
                    long longValue = stack.popLong();
                    ref = stack.popObjectRef();
                    if (ref.isNull()) {
                        throw new NullPointerException();
                    }
                    ref.getFields().setLong(slotID, longValue);
                    break;
                case 'D':
                    double doubleValue = stack.popDouble();
                    ref = stack.popObjectRef();
                    if (ref.isNull()) {
                        throw new NullPointerException();
                    }
                    ref.getFields().setDouble(slotID, doubleValue);
                    break;
                case 'L':
                case '[':
                    JObject refVal = stack.popObjectRef();
                    ref = stack.popObjectRef();
                    if (ref.isNull()) {
                        throw new NullPointerException();
                    }
                    ref.getFields().setObjectRef(slotID, refVal);
                    break;
                default:
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
