package com.arnest.project.factory;

import com.arnest.project.command.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    public static Command getCommand(String[] args) {

        if (args.length == 0) {
            System.out.println("running all");
            return new RunAllTestsCommand();
        }
        Map<String, String> argMap = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            if (i + 1 < args.length && args[i].startsWith("-")) {
                argMap.put(args[i], args[i + 1]);
            } else {
                argMap.put(args[i], null);
            }
        }


        if (argMap.containsKey("-s") && argMap.containsKey("-g")) {
            String testCase = argMap.get("-s");
            String group = argMap.get("-g");

            if (testCase == null || group == null) {
                System.out.println("Miss -s or -g parameter.");
                printUsage();
                return null;
            }

            System.out.println("running testcase + group");
            return new RunTestGroupCommand(testCase, group);
        }
        if (argMap.containsKey("-s")) {
            String testCase = argMap.get("-s");
            if (testCase == null) {
                System.out.println("Mis -s parameter.");
                printUsage();
                return null;
            }

            System.out.println("running testcase");
            return new RunTestCaseCommand(testCase);
        }

        if (argMap.containsKey("-g")) {
            System.out.println("running group");
            return new RunTestGroupOnlyCommand(argMap.get("-g"));
        }


        System.out.println("Error: Invalid command usage.");
        printUsage();
        return null;

    }

    private static void printUsage() {
        System.out.println("No arg  all suite will get executed");
        System.out.println("-s suitename that suite will be executed");
        System.out.println("-s suitename -g groupname that suite with gropname will be executed");
        System.out.println("-g groupname that group will be executed");
    }
}
