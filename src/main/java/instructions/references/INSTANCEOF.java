package instructions.references;

import instructions.base.Index16Instruction;
import memory.jclass.JClass;
import memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import memory.jclass.runtimeConstantPool.constant.ref.ClassRef;
import runtime.OperandStack;
import runtime.StackFrame;
import runtime.struct.JObject;

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