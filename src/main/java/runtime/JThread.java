package runtime;

public class JThread {
    private int pc;
    private ThreadStack stack;

    public JThread(){
        stack = new ThreadStack();
    }

    public void pushFrame(StackFrame frame){
        stack.pushFrame(frame);
    }

    public void popFrame(){
        stack.popFrame();
    }

    public StackFrame getTopFrame(){
        return stack.getTopFrame();
    }
}
