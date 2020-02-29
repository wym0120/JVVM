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

public class PUTSTATIC extends Index16Instruction {
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
