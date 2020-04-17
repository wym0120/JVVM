package instructions.invoke;

import instructions.base.Index16Instruction;
import memory.jclass.JClass;
import memory.jclass.Method;
import memory.jclass.runtimeConstantPool.constant.Constant;
import memory.jclass.runtimeConstantPool.constant.ref.MethodRef;
import runtime.StackFrame;
import runtime.Vars;
import runtime.struct.JObject;
import runtime.struct.Slot;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-03-28
 */
public class INVOKE_VIRTUAL extends INVOKE_BASE {
    @Override
    public void execute(StackFrame frame) {
        Method method = getMethod(frame);

        //lookup virtual function
//        JObject objectRef = frame.getOperandStack().popObjectRef();
//        JClass clazz = objectRef.getClazz();
//        Method toInvoke = ((MethodRef) methodRef).resolveMethodRef(clazz);

        invokeMethod(frame, method);
    }

}
