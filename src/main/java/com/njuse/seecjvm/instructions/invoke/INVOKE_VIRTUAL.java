package com.njuse.seecjvm.instructions.invoke;

import com.njuse.seecjvm.instructions.base.Index16Instruction;
import com.njuse.seecjvm.memory.jclass.JClass;
import com.njuse.seecjvm.memory.jclass.Method;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.Constant;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.ref.MethodRef;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.Vars;
import com.njuse.seecjvm.runtime.struct.JObject;
import com.njuse.seecjvm.runtime.struct.Slot;

public class INVOKE_VIRTUAL extends Index16Instruction {
    @Override
    public void execute(StackFrame frame) {
        JClass currentClz = frame.getMethod().getClazz();
        Constant methodRef = currentClz.getRuntimeConstantPool().getConstant(super.index);
        assert methodRef instanceof MethodRef;
        Method method = ((MethodRef) methodRef).resolveMethodRef();

        //copy arguments
        int argc = method.getArgc();
        Slot[] argv = new Slot[argc];
        for (int i = 0; i < argc; i++) {
            argv[i] = frame.getOperandStack().popSlot();
        }
        //lookup virtual function
        JObject objectRef = frame.getOperandStack().popObjectRef();
        JClass clazz = objectRef.getClazz();
        Method toInvoke = ((MethodRef) methodRef).resolveMethodRef(clazz);

        StackFrame newFrame = prepareNewFrame(frame, argc, argv, objectRef, toInvoke);

        frame.getThread().pushFrame(newFrame);

        if (method.isNative()) {
            if (method.getName().equals("registerNatives")) {
                frame.getThread().popFrame();
            } else {
                System.out.println("Native method:"
                        + method.getClazz().getName()
                        + method.name
                        + method.descriptor);
                frame.getThread().popFrame();
            }
        }
    }

    private StackFrame prepareNewFrame(StackFrame frame, int argc, Slot[] argv, JObject objectRef, Method toInvoke) {
        StackFrame newFrame = new StackFrame(frame.getThread(), toInvoke,
                toInvoke.getMaxStack(), toInvoke.getMaxLocal() + 1);
        Vars localVars = newFrame.getLocalVars();
        Slot thisSlot = new Slot();
        thisSlot.setObject(objectRef);
        localVars.setSlot(0, thisSlot);
        for (int i = 1; i < argc + 1; i++) {
            localVars.setSlot(i, argv[argc - i]);
        }
        return newFrame;
    }
}
