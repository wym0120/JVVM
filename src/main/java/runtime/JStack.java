package runtime;

import java.util.EmptyStackException;
import java.util.Stack;

public class JStack {
    private static int maxSize = 100;
    private Stack<JFrame> stack;
    private int currentSize;

    public JStack(){
        stack = new Stack<>();
    }

    public static void setMaxSize(int maxSize){
        if(maxSize <= 0)throw new EmptyStackException();
        JStack.maxSize = maxSize;
    }

    public void pushFrame(JFrame frame){
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

    public JFrame getTopFrame(){
        if(currentSize == 0) throw new EmptyStackException();
        return stack.lastElement();
    }
}
