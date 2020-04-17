package instructions.references;

import instructions.base.Index16Instruction;
import memory.jclass.Field;
import memory.jclass.InitState;
import memory.jclass.JClass;
import memory.jclass.Method;
import memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import memory.jclass.runtimeConstantPool.constant.ref.FieldRef;
import runtime.OperandStack;
import runtime.StackFrame;
import runtime.Vars;

public class GETSTATIC extends Index16Instruction {
    @Override
    public void execute(StackFrame frame) {
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        FieldRef fieldRef = (FieldRef) runtimeConstantPool.getConstant(index);
        Field field = null;
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
                    stack.pushInt(staticVars.getInt(slotID));
                    break;
                case 'F':
                    stack.pushFloat(staticVars.getFloat(slotID));
                    break;
                case 'J':
                    stack.pushLong(staticVars.getLong(slotID));
                    break;
                case 'D':
                    stack.pushDouble(staticVars.getDouble(slotID));
                    break;
                case 'L':
                case '[':
                    stack.pushObjectRef(staticVars.getObjectRef(slotID));
                    break;
                default:
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
