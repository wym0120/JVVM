import classloader.attribute.CodeAttribute;
import runtime.JThread;
import runtime.StackFrame;

import java.nio.ByteBuffer;

public class Interpreter {

    public static void interpret(CodeAttribute codeAttribute){
        int maxLocal = codeAttribute.getMaxLocal();
        int maxStack = codeAttribute.getMaxStack();
        byte[] code  = codeAttribute.getCode();
        JThread thread = new JThread();
        StackFrame frame = new StackFrame(thread,maxStack,maxLocal);
        thread.pushFrame(frame);
        //loop
        loop(thread,code);
    }

    private static void loop(JThread thread,byte[] code){
        ByteBuffer reader = ByteBuffer.wrap(code);

    }
}
