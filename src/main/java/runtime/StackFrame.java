package runtime;

import lombok.Data;
import memory.jclass.Method;

@Data
public class StackFrame {
    private OperandStack operandStack;
    private Vars localVars;
    private StackFrame lower;
    private JThread thread;
    private int nextPC;
    private Method method;

    public StackFrame(JThread thread, Method method, int maxStackSize, int maxVarSize) {
        this.thread = thread;
        this.method = method;
        operandStack = new OperandStack(maxStackSize);
        localVars = new Vars(maxVarSize);
    }

    public void revertNextPC() {
        this.nextPC = this.thread.getPc();
    }
}
