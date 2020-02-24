package runtime;

import lombok.Data;

@Data
public class StackFrame {
    private OperandStack operandStack;
    private Vars localVars;
    private StackFrame lower;
    private JThread thread;
    private int nextPC;

    public StackFrame(JThread thread,int maxStackSize, int maxVarSize){
        this.thread = thread;
        operandStack = new OperandStack(maxStackSize);
        localVars = new Vars(maxVarSize);
    }
}
