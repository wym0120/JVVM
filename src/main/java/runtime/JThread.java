package runtime;

public class JThread {
    private int pc;
    private JStack stack;

    public JThread(){
        stack = new JStack();
    }

    public void pushFrame(JFrame frame){
        stack.pushFrame(frame);
    }

    public void popFrame(){
        stack.popFrame();
    }

    public JFrame getTopFrame(){
        return stack.getTopFrame();
    }
}
