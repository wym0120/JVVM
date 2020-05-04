package vo;

import execution.Recorder;
import runtime.JThread;
import runtime.StackFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ThreadVO {
    private List<FrameVO> threadStack;

    public ThreadVO(JThread thread) {
        threadStack = new ArrayList<>();
        Stack<StackFrame> stack = thread.getStack().getStack();
        Stack<Boolean> frameState = thread.getStack().getFrameState();
        assert stack.size() == frameState.size();
        for (int i = 0; i < stack.size(); i++) {
            threadStack.add(Recorder.generateFrameVO(stack.get(i), frameState.get(i)));
        }
    }
}
