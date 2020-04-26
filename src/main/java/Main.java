import classloader.ClassLoader;
import memory.jclass.JClass;
import memory.jclass.Method;
import runtime.JThread;
import runtime.StackFrame;


public class Main {
    public static void main(String[] args) {
        CommandlineUtil.parseInput(args);
        CommandlineUtil.handleOptions();
        String[] userArgs = CommandlineUtil.readArgs();
        //检查是否有op之外的输入
        if (userArgs.length == 0) {
            System.out.println("Please check your input. You can use jvvm -h for more information");
            System.exit(-1);
        }
        String className = userArgs[0];
        try {
            runClass(className);
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot find target class,Please check your className or classpath.");
            e.printStackTrace();
        }

    }

    private static void runClass(String className) throws ClassNotFoundException {
        ClassLoader loader = ClassLoader.getInstance();
        JClass clazz = loader.loadClass(className, null);
        JThread thread = new JThread();
        Method main = clazz.getMainMethod();
        StackFrame mainFrame = new StackFrame(thread, main, main.getMaxStack(), main.getMaxLocal());
        thread.pushFrame(mainFrame);
        Interpreter interpreter = new Interpreter();
        interpreter.interpret(thread);
    }
}
