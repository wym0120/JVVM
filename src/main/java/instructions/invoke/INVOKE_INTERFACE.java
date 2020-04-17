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
public class INVOKE_INTERFACE extends INVOKE_BASE {

    @Override
    public void fetchOperands(ByteBuffer reader) {
        super.fetchOperands(reader);
        reader.get();
        reader.get();
    }

    @Override
    public void execute(StackFrame frame) {
        Method method = getMethod(frame);
        //TODO add check for static/access

        //lookup interface function
//        JObject objectRef = frame.getOperandStack().popObjectRef();
//        JClass clazz = objectRef.getClazz();
//        Method toInvoke = ((MethodRef) methodRef).resolveMethodRef(clazz);

        invokeMethod(frame, method);
    }
}
