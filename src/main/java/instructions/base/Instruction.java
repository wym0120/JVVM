package instructions.base;

import runtime.StackFrame;

import java.nio.ByteBuffer;

public abstract class Instruction {
    public abstract void execute(StackFrame frame);
    public abstract void fetchOperands(ByteBuffer reader);

//    public static Instruction getInstruction(){
//        return null;
//    }
}
