package runtime;

import java.util.EmptyStackException;
import java.util.Stack;

public class ThreadStack {
    private static int maxSize;
    private Stack<StackFrame> stack;
    private int currentSize;

    public ThreadStack(){
        stack = new Stack<>();
//        ThreadStack.maxSize = maxSize;
    }


    public static void setMaxSize(int maxSize){
        if(maxSize <= 0)throw new EmptyStackException();
        ThreadStack.maxSize = maxSize;
    }

    public boolean isEmpty(){
        return currentSize == 0;
    }

    public void pushFrame(StackFrame frame){
        if(currentSize >= maxSize){
            throw new StackOverflowError();
        }
        stack.push(frame);
        currentSize++;
    }

    public void popFrame(){
        if(currentSize == 0){
            throw new EmptyStackException();
        }
        stack.pop();
        currentSize--;
    }

    public StackFrame getTopFrame(){
        if(currentSize == 0) return null;
        return stack.lastElement();
    }
}
