package com.njuse.seecjvm.instructions.invoke;

import com.njuse.seecjvm.memory.jclass.InitState;
import com.njuse.seecjvm.memory.jclass.JClass;
import com.njuse.seecjvm.memory.jclass.Method;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.Constant;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.ref.MethodRef;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.struct.Slot;

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
                    throw new RuntimeException();
                }
                frame.getOperandStack().pushInt(v2);
                frame.getOperandStack().pushInt(v1);

            } else if (toInvoke.getName().contains("equalFloat")) {
                float v1 = frame.getOperandStack().popFloat();
                float v2 = frame.getOperandStack().popFloat();
                if (v1 - v2 > 0.0001 || v1 - v2 < -0.0001) {
                    throw new RuntimeException();
                }
                frame.getOperandStack().pushFloat(v2);
                frame.getOperandStack().pushFloat(v1);

            } else if (toInvoke.getName().equals("fail")) {
                throw new RuntimeException();
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
