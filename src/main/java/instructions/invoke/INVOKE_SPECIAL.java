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
public class INVOKE_SPECIAL extends Index16Instruction {
    @Override
    public void execute(StackFrame frame) {
        //noinspection Duplicates
        JClass currentClz = frame.getMethod().getClazz();
        Constant methodRef = currentClz.getRuntimeConstantPool().getConstant(super.index);
        assert methodRef instanceof MethodRef;
        Method method = ((MethodRef) methodRef).resolveMethodRef();

        JClass c;
        if (frame.getMethod().getClazz().isAccSuper()
                && !method.getName().equals("<init>")) {
            c = frame.getMethod().getClazz().getSuperClass();
        } else {
            c = method.getClazz();
        }
        Method toInvoke = ((MethodRef) methodRef).resolveMethodRef(c);

        Slot[] args = copyArguments(frame, method);


        StackFrame newFrame = new StackFrame(frame.getThread(), toInvoke,
                toInvoke.getMaxStack(), toInvoke.getMaxLocal() + 1);
        Vars localVars = newFrame.getLocalVars();
        JObject thisRef = frame.getOperandStack().popObjectRef();

        Slot slot = new Slot();
        slot.setObject(thisRef);
        localVars.setSlot(0, slot);
        int argc = method.getArgc();
        for (int i = 1; i < args.length + 1; i++) {
            localVars.setSlot(i, args[argc - i]);
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
}
