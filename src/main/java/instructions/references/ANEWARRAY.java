package instructions.references;

import instructions.base.Index16Instruction;
import memory.jclass.JClass;
import memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import memory.jclass.runtimeConstantPool.constant.ref.ClassRef;
import runtime.OperandStack;
import runtime.StackFrame;
import runtime.struct.JObject;

public class ANEWARRAY extends Index16Instruction {
    @Override
    public void execute(StackFrame frame) {
        RuntimeConstantPool runtimeConstantPool= frame.getMethod().getClazz().getRuntimeConstantPool();
        ClassRef classRef = (ClassRef) runtimeConstantPool.getConstant(index);
        try {
            JClass componentClass = classRef.getResolvedClass();
            OperandStack stack = frame.getOperandStack();
            int len = stack.popInt();
            if(len<0)throw new NegativeArraySizeException();
            JClass arrClass = componentClass.getArrayClass();
            JObject arr = arrClass.newArrayObject(len);
            stack.pushObjectRef(arr);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
