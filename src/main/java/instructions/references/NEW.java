package instructions.references;

import instructions.base.Index16Instruction;
import memory.jclass.JClass;
import memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import memory.jclass.runtimeConstantPool.constant.ref.ClassRef;
import runtime.StackFrame;
import runtime.struct.JObject;

public class NEW extends Index16Instruction {
    @Override
    public void execute(StackFrame frame) {
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        ClassRef classRef = (ClassRef) runtimeConstantPool.getConstant(index);
        try {
            JClass clazz = classRef.getResolvedClass();
            if (clazz.isInterface() || clazz.isAbstract()) {
                throw new InstantiationError();
            }
            JObject ref = clazz.newObject();
            frame.getOperandStack().pushObjectRef(ref);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
