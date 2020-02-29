package instructions.references;

import instructions.base.Index16Instruction;
import memory.jclass.Field;
import memory.jclass.JClass;
import memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import memory.jclass.runtimeConstantPool.constant.ref.FieldRef;
import runtime.OperandStack;
import runtime.StackFrame;
import runtime.Vars;
import runtime.struct.JObject;

public class GETFIELD extends Index16Instruction {
    @Override
    public void execute(StackFrame frame) {
        RuntimeConstantPool currentRuntimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        FieldRef fieldRef = (FieldRef) currentRuntimeConstantPool.getConstant(index);
        Field field = null;
        try {
            field = fieldRef.getResolvedFieldRef();
            JClass targetClazz = field.getClazz();
            if (field.isStatic()) {
                throw new IncompatibleClassChangeError();
            }
            OperandStack stack = frame.getOperandStack();
            JObject ref = stack.popObjectRef();
            //todo:nullpointerException need to be check whether set JObject isNull field can trigger this exception!!!!!!
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
