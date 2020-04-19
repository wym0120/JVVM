package instructions.invoke;

import memory.jclass.Method;
import runtime.StackFrame;

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
        invokeMethod(frame, initializeFrame(frame, method), method);

    }
}
