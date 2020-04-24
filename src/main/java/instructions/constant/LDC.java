package instructions.constant;

import instructions.base.Index8Instruction;
import memory.jclass.runtimeConstantPool.constant.Constant;
import memory.jclass.runtimeConstantPool.constant.wrapper.FloatWrapper;
import memory.jclass.runtimeConstantPool.constant.wrapper.IntWrapper;
import memory.jclass.runtimeConstantPool.constant.wrapper.StringWrapper;
import runtime.OperandStack;
import runtime.StackFrame;
import runtime.struct.StringObject;

public class LDC extends Index8Instruction {
    @Override
    public void execute(StackFrame frame) {
        loadConstant(frame, index);
    }

    public static void loadConstant(StackFrame frame, int index) {
        OperandStack stack = frame.getOperandStack();
        Constant constant = frame.getMethod().getClazz().getRuntimeConstantPool().getConstant(index);
        if (constant instanceof IntWrapper) stack.pushInt(((IntWrapper) constant).getValue());
        else if (constant instanceof FloatWrapper) stack.pushFloat(((FloatWrapper) constant).getValue());
        else if(constant instanceof StringWrapper)
            stack.pushObjectRef(new StringObject(((StringWrapper) constant).getValue()));
//        if(constant instanceof ClassRef)
        else throw new ClassFormatError();

    }
}
