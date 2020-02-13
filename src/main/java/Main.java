import classloader.ClassFileReader;

import java.io.IOException;
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
        System.out.println("Class : " + className);
        byte[] classFile = new byte[0];
        try {
            classFile = ClassFileReader.readClassFile(className);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(classFile));
    }


}
