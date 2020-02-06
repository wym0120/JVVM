import classpath.ClassFileReader;

import java.util.Arrays;

public class Main {
    public static void main(String args[]){
        CommandLineUtil.initCommandLine(args);
        CommandLineUtil.handleOptions();
        String[] userArgs = CommandLineUtil.readArgs();
        //检查是否有op之外的输入
        if(userArgs.length == 0){
            System.out.println("Please check your input. You can use jvvm -h for more information");
            System.exit(-1);
        }
        String className = userArgs[0];
        byte[] classFile = ClassFileReader.readClassFile(className);
        //todo:翔哲！！！来写代码啦
    }

//    private static void startJVVM(String[] args){
//
//    }

}
