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
public class INVOKE_SPECIAL extends INVOKE_BASE {
    @Override
    public void execute(StackFrame frame) {
        //noinspection Duplicates
        Method method = getMethod(frame);

//        JClass c;
//        if (frame.getMethod().getClazz().isAccSuper()
//                && !method.getName().equals("<init>")) {
//            c = frame.getMethod().getClazz().getSuperClass();
//        } else {
//            c = method.getClazz();
//        }
//        Method toInvoke = ((MethodRef) methodRef).resolveMethodRef(c);
//
//        invokeMethod(frame,toInvoke);
        invokeMethod(frame, method);

    }
}
