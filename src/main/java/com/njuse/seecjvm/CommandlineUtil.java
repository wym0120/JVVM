package com.njuse.seecjvm;

import com.njuse.seecjvm.classloader.classfilereader.ClassFileReader;
import org.apache.commons.cli.*;

public class CommandlineUtil {
    private static CommandLine commandLine;
    private static CommandLineParser parser = new DefaultParser();
    private static Options options = new Options();

    static {
        options.addOption("h", "help", false, "Print help message");
        options.addOption("cp", "classpath", true, "Specify the user classpath");
        options.addOption("v", "version", false, "Print version and exit");
        options.addOption("Xjre", true, "Specify extended classpath");
    }

    /**
     * print the usage of every option
     */
    private static void printHelpMessage() {
        String header = "\njava [-options] class [args...]\n" +
                "   or java [-options] -jar jarfile [args...]\n";
        String footer = "\nRefer to help doc for more information";
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("JVVM", header, options, footer);
        System.exit(0);
    }

    private static void printVersion() {
        System.out.println("version 0.0.1\n");
        System.exit(0);
    }

    /**
     * We use -Xjre to set Bootstrap entry path and Extension entry path together
     * You can add option -Xbootclasspath set the path of Bootstrap loader
     *
     * @param classPath classpath of JDK
     */
    private static void setBootAndExtEntry(String classPath) {
        ClassFileReader.setBootAndExtClasspath(classPath);
    }

    /**
     * @param classPath classpath of user Class
     */
    private static void setUserEntry(String classPath) {
        ClassFileReader.setUserClasspath(classPath);
    }

    public static String[] readArgs() {
        String[] ret = new String[0];
        if (commandLine.getArgs().length > 0) {
            ret = commandLine.getArgs();
        }
        return ret;
    }


    public static void parseInput(String[] args) {
        try {
            commandLine = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    public static void handleOptions() {
        if (commandLine.hasOption("h") || commandLine.hasOption("help")) printHelpMessage();
        if (commandLine.hasOption("v") || commandLine.hasOption("version")) printVersion();
        if (commandLine.hasOption("cp") || commandLine.hasOption("classpath"))
            setUserEntry(commandLine.getOptionValue("cp"));
        if (commandLine.hasOption("Xjre")) setBootAndExtEntry(commandLine.getOptionValue("Xjre"));
    }


}
