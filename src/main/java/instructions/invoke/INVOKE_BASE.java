package instructions.invoke;

import instructions.base.Index16Instruction;
import memory.jclass.JClass;
import memory.jclass.Method;
import memory.jclass.runtimeConstantPool.constant.Constant;
import memory.jclass.runtimeConstantPool.constant.ref.InterfaceMethodRef;
import memory.jclass.runtimeConstantPool.constant.ref.MemberRef;
import memory.jclass.runtimeConstantPool.constant.ref.MethodRef;
import runtime.JThread;
import runtime.StackFrame;
import runtime.struct.Slot;

public abstract class INVOKE_BASE extends Index16Instruction {
    public void invokeMethod(StackFrame callerFrame, StackFrame calleeFrame, Method method) {
        JThread thread = callerFrame.getThread();
        thread.pushFrame(calleeFrame);
        calleeFrame.setMethod(method);
        //hack native register
        if (method.isNative()) {
            if (method.getName().equals("registerNatives")) {
                thread.popFrame();
            } else {
                System.out.println("Native method:"
                        + method.getClazz().getName()
                        + method.name
                        + method.descriptor);
                thread.popFrame();
            }
        }

    }

    /**
     * create frame & pass args
     */
    public StackFrame initializeFrame(StackFrame caller, Method method) {
        //prepare new frame
        JThread thread = caller.getThread();
        StackFrame newFrame = new StackFrame(thread, method, method.getMaxStack(), method.getMaxLocal());


        //pass arguments
        int argc = method.getArgc();
        for (int i = argc - 1; i >= 0; i--) {
            Slot slot = caller.getOperandStack().popSlot();
            newFrame.getLocalVars().setSlot(i, slot);
        }
        return newFrame;
    }

    /**
     * resolve the ref of method
     *
     * @param frame target frame
     * @return resolved method
     */
    public Method getMethod(StackFrame frame) {
        JClass currentClz = frame.getMethod().getClazz();
        Constant ref = currentClz.getRuntimeConstantPool().getConstant(super.index);
        assert ref instanceof MemberRef;
        if (ref instanceof MethodRef) return ((MethodRef) ref).resolveMethodRef();
        else if (ref instanceof InterfaceMethodRef) return ((InterfaceMethodRef) ref).resolveInterfaceMethodRef();
        throw new NoSuchMethodError();
    }
}
