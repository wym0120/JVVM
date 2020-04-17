package instructions.invoke;

import instructions.base.Index16Instruction;
import memory.jclass.InitState;
import memory.jclass.JClass;
import memory.jclass.Method;
import memory.jclass.runtimeConstantPool.constant.Constant;
import memory.jclass.runtimeConstantPool.constant.ref.MethodRef;
import runtime.StackFrame;
import runtime.Vars;
import runtime.struct.Slot;

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

        //check class whether init
        JClass currentClazz = toInvoke.getClazz();
        if (currentClazz.getInitState() == InitState.PREPARED) {
            frame.setNextPC(frame.getNextPC() - 3);//opcode + operand = 3bytes
            currentClazz.initClass(frame.getThread(), currentClazz);
            return;
        }

        invokeMethod(frame, toInvoke);
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
