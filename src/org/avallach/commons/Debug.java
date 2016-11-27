package org.avallach.commons;

import java.lang.management.ManagementFactory;
import java.util.*;

public class Debug
{
    public static final boolean DEBUG = ManagementFactory.getRuntimeMXBean()
            .getInputArguments()
            .toString()
            .indexOf("-agentlib:jdwp") > 0;

    public static void log(Object ... message)
    {
        if (DEBUG)
            System.out.println(getCallerInfo() +
                    ((message.length > 0) ? "\t: " + String.valueOf(Arrays.toString(message)) : ""));
    }

    private static String getCallerInfo()
    {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        //0 is Thread.getStackTrace, 1 is this method, 2 is direct caller, 3 is its caller
        int callerStackDepth = 3;
        while (stackTrace[callerStackDepth].getFileName().equals("Debug.java"))
            callerStackDepth++;
        StackTraceElement caller = stackTrace[callerStackDepth];
        return ".(" + caller.getFileName() + ":" + caller.getLineNumber() + ") " // IntelliJ displays .(:) as hyperlink
                + caller.getMethodName();
    }
}