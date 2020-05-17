package com.njuse.seecjvm.runtime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JThread {
    private ThreadStack stack;

    public JThread() {
        stack = new ThreadStack();
    }

    public void pushFrame(StackFrame frame) {
        stack.pushFrame(frame);
    }

    public void popFrame() {
        stack.popFrame();
    }

    public StackFrame getTopFrame() {
        return stack.getTopFrame();
    }
}
