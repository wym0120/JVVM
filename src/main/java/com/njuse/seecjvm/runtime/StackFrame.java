package com.njuse.seecjvm.runtime;

import com.njuse.seecjvm.memory.jclass.Method;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StackFrame {
    private OperandStack operandStack;
    private Vars localVars;
    private JThread thread;
    private int nextPC;
    private Method method;

    public StackFrame(JThread thread, Method method, int maxStackSize, int maxVarSize) {
        this.thread = thread;
        this.method = method;
        operandStack = new OperandStack(maxStackSize);
        localVars = new Vars(maxVarSize);
    }

}
