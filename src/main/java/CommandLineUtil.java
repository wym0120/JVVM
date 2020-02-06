import org.apache.commons.cli.*;

/**
 * wym
 */
public class CommandLineUtil {
    private static CommandLineParser parser = new DefaultParser();
    private static Options options = new Options();
    private static void initOptions(){
        options.addOption("h","help",false,"Print help message");
        options.addOption("cp","classpath",true,"Specify the classpath");
        options.addOption("v","version",false,"Print version and exit");
    }

    private static void printHelpMessage(){
        String header = "\njava [-options] class [args...]\n" +
                "   or java [-options] -jar jarfile [args...]\n" ;
        //todo:翔哲快来看看这句五毛英语通不通顺
        String footer = "\nRefer to help doc for more information";

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("JVVM",header,options,footer);
    }

    private static void printVersion(){
        System.out.println("version 0.0.1\n");
        System.exit(0);
    }

    public static void readArgs(String[] args){
        initOptions();
        try {
            CommandLine commandLine = parser.parse(options,args);
            if(commandLine.hasOption("h")||commandLine.hasOption("help"))printHelpMessage();
            if(commandLine.hasOption("v")||commandLine.hasOption("version"))printVersion();
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Unexpected exception"+e.getMessage());
        }

    }



}
