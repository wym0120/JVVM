package com.njuse.seecjvm;

import com.njuse.seecjvm.classloader.ClassLoader;
import com.njuse.seecjvm.execution.Interpreter;
import com.njuse.seecjvm.memory.jclass.JClass;
import com.njuse.seecjvm.memory.jclass.Method;
import com.njuse.seecjvm.runtime.JThread;
import com.njuse.seecjvm.runtime.StackFrame;


public class Main {
    public static void main(String[] args) {
        System.getenv("JAVA_HOME");
        CommandlineUtil.parseInput(args);
        CommandlineUtil.handleOptions();
        String[] userArgs = CommandlineUtil.readArgs();
        if (userArgs.length == 0) {
            System.out.println("Please check your input. You can use jvvm -h for more information");
            return;
        }
        String className = userArgs[0];
        try {
            startJVM(className);
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot find target class,Please check your className or classpath.");
            e.printStackTrace();
        }

    }

    private static void startJVM(String className) throws ClassNotFoundException {
        ClassLoader loader = ClassLoader.getInstance();
        JClass clazz = loader.loadClass(className, null);
        JThread thread = new JThread();
        //find main method
        Method main = clazz.getMainMethod();
        StackFrame mainFrame = new StackFrame(thread, main, main.getMaxStack(), main.getMaxLocal());
        thread.pushFrame(mainFrame);
        clazz.initClass(thread, clazz);
        Interpreter.interpret(thread);
    }
}
