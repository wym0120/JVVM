package instructions.invoke;

import memory.jclass.InitState;
import memory.jclass.JClass;
import memory.jclass.Method;
import memory.jclass.runtimeConstantPool.constant.Constant;
import memory.jclass.runtimeConstantPool.constant.ref.MethodRef;
import runtime.StackFrame;
import runtime.struct.Slot;
import sun.jvm.hotspot.utilities.AssertionFailure;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-03-28
 */
public class INVOKE_STATIC extends INVOKE_BASE {
    @Override
    public void execute(StackFrame frame) {
        Method toInvoke = getMethod(frame);

        JClass currentClz = frame.getMethod().getClazz();
        Constant ref = currentClz.getRuntimeConstantPool().getConstant(super.index);
        assert ref instanceof MethodRef;
        if (((MethodRef) ref).getClassName().contains("TestUtil")) {
            if (toInvoke.getName().contains("equalInt")) {
                int v1 = frame.getOperandStack().popInt();
                int v2 = frame.getOperandStack().popInt();
                if (v1 != v2) {
                    throw new AssertionFailure();
                }
                frame.getOperandStack().pushInt(v2);
                frame.getOperandStack().pushInt(v1);

            } else if (toInvoke.getName().equals("fail")) {
                throw new AssertionFailure();
            }
        }


        //check class whether init
        JClass currentClazz = toInvoke.getClazz();
        if (currentClazz.getInitState() == InitState.PREPARED) {
            frame.setNextPC(frame.getNextPC() - 3);//opcode + operand = 3bytes
            currentClazz.initClass(frame.getThread(), currentClazz);
            return;
        }

        invokeMethod(frame, initializeFrame(frame, toInvoke), toInvoke);
    }

    private Slot[] copyArguments(StackFrame frame, Method method) {
        int argc = method.getArgc();
        Slot[] argv = new Slot[argc];
        for (int i = 0; i < argc; i++) {
            argv[i] = frame.getOperandStack().popSlot();
        }
        return argv;
    }

}
