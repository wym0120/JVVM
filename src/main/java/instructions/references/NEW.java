package instructions.references;

import instructions.base.Index16Instruction;
import memory.jclass.InitState;
import memory.jclass.JClass;
import memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import memory.jclass.runtimeConstantPool.constant.ref.ClassRef;
import runtime.StackFrame;
import runtime.struct.NonArrayObject;

public class NEW extends Index16Instruction {
    @Override
    public void execute(StackFrame frame) {
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        ClassRef classRef = (ClassRef) runtimeConstantPool.getConstant(index);
        try {
            JClass clazz = classRef.getResolvedClass();

            //check class whether init
            if (clazz.getInitState() == InitState.PREPARED) {
                //todo:test whether 2 is revert 2 bytes here
                frame.setNextPC(frame.getNextPC() - 2);
                clazz.initClass(frame.getThread(), clazz);
                return;
            }

            if (clazz.isInterface() || clazz.isAbstract()) {
                throw new InstantiationError();
            }
            NonArrayObject ref = clazz.newObject();
            frame.getOperandStack().pushObjectRef(ref);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
