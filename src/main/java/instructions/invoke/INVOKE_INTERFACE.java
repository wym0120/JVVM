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

import java.nio.ByteBuffer;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-03-29
 */
public class INVOKE_INTERFACE extends Index16Instruction {

    @Override
    public void fetchOperands(ByteBuffer reader) {
        super.fetchOperands(reader);
        reader.get();
        reader.get();
    }

    @Override
    public void execute(StackFrame frame) {
        JClass currentClz = frame.getMethod().getClazz();
        Constant methodRef = currentClz.getRuntimeConstantPool().getConstant(super.index);
        assert methodRef instanceof MethodRef;
        Method method = ((MethodRef) methodRef).resolveMethodRef();
        //TODO add check for static/access

        //copy arguments
        int argc = method.getArgc();
        Slot[] argv = new Slot[argc];
        for (int i = 0; i < argc; i++) {
            argv[i] = frame.getOperandStack().popSlot();
        }
        //lookup interface function
        JObject objectRef = frame.getOperandStack().popObjectRef();
        JClass clazz = objectRef.getClazz();
        Method toInvoke = ((MethodRef) methodRef).resolveMethodRef(clazz);

        StackFrame newFrame = prepareNewFrame(frame, argc, argv, objectRef, toInvoke);

        frame.getThread().pushFrame(newFrame);
    }


    private StackFrame prepareNewFrame(StackFrame frame, int argc, Slot[] argv, JObject objectRef, Method toInvoke) {
        StackFrame newFrame = new StackFrame(frame.getThread(), toInvoke,
                toInvoke.getMaxStack(), toInvoke.getMaxLocal() + 1);
        Vars localVars = newFrame.getLocalVars();
        Slot thisSlot = new Slot();
        thisSlot.setObject(objectRef);
        localVars.setSlot(0, thisSlot);
        for (int i = 1; i < argc + 1; i++) {
            localVars.setSlot(i, argv[i - 1]);
        }
        return newFrame;
    }
}
