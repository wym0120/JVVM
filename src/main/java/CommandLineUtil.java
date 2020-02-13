import classloader.ClassFileReader;
import org.apache.commons.cli.*;

/**
 * @author wym
 */
public class CommandLineUtil {
    private static CommandLine commandLine;
    private static CommandLineParser parser = new DefaultParser();
    private static Options options = new Options();
    public static void initCommandLine(String args[]){
        options.addOption("h","help",false,"Print help message");
        options.addOption("cp","classpath",true,"Specify the user classpath");
        options.addOption("v","version",false,"Print version and exit");
        //todo:留作作业中的扩展
        //options.addOption("Xbootclasspath",true,"Specify boot classpath");
        options.addOption("Xjre",true,"Specify extended classpath");

        //初始化commandline
        try {
            commandLine = parser.parse(options,args);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Unexpected exception"+e.getMessage());
        }


    }

    private static void printHelpMessage(){
        String header = "\njava [-options] class [args...]\n" +
                "   or java [-options] -jar jarfile [args...]\n" ;
        String footer = "\nRefer to help doc for more information";

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("JVVM",header,options,footer);
    }

    private static void printVersion(){
        System.out.println("version 0.0.1\n");
        System.exit(0);
    }

    private static void setExtEntry(String classPath){
        ClassFileReader.setExtClasspath(classPath);

    }

    private static void setUserEntry(String classPath){
        ClassFileReader.setUserClasspath(classPath);
    }

    public static String[] readArgs(){
        String[] ret = new String[0];
        if (commandLine.getArgs().length>0){
            ret = commandLine.getArgs();
        }
        return ret;
    }

    public static void handleOptions(){
        if(commandLine.hasOption("h")||commandLine.hasOption("help"))printHelpMessage();
        if(commandLine.hasOption("v")||commandLine.hasOption("version"))printVersion();
        if(commandLine.hasOption("cp")||commandLine.hasOption("classpath")) setUserEntry(commandLine.getOptionValue("cp"));
        if(commandLine.hasOption("Xjre"))setExtEntry(commandLine.getOptionValue("Xjre"));
    }


}
