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
public class INVOKE_STATIC extends Index16Instruction {
    @Override
    public void execute(StackFrame frame) {
        Method method = getMethod(frame);

        Slot[] args = copyArguments(frame, method);

        StackFrame newFrame = new StackFrame(frame.getThread(), method,
                method.getMaxStack(), method.getMaxLocal());
        Vars localVars = newFrame.getLocalVars();
        for (int i = 0; i < args.length; i++) {
            localVars.setSlot(i, args[i]);
        }

        frame.getThread().pushFrame(newFrame);
    }

    private Slot[] copyArguments(StackFrame frame, Method method) {
        int argc = method.getArgc();
        Slot[] argv = new Slot[argc];
        for (int i = 0; i < argc; i++) {
            argv[i] = frame.getOperandStack().popSlot();
        }
        return argv;
    }

    private Method getMethod(StackFrame frame) {
        JClass currentClz = frame.getMethod().getClazz();
        Constant methodRef = currentClz.getRuntimeConstantPool().getConstant(super.index);
        assert methodRef instanceof MethodRef;
        return ((MethodRef) methodRef).resolveMethodRef();
    }
}
