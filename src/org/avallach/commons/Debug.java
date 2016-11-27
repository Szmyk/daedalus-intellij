package org.avallach.commons;

import sun.misc.JavaLangAccess;
import sun.misc.SharedSecrets;

import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        try {
            while (stackTrace[callerStackDepth].getFileName().equals("Debug.java"))
                callerStackDepth++;
        } catch(NullPointerException wtf)
        {
            Debug.log(wtf);
        }
        StackTraceElement caller = stackTrace[callerStackDepth];
        return ".(" + caller.getFileName() + ":" + caller.getLineNumber() + ") " // IntelliJ displays .(:) as hyperlink
                + caller.getMethodName();
    }

    private static DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
    public static void logTime(Object... message)
    {
        System.out.println(getCallerInfo() + "\t: " +
                "Time: " + dateFormat.format(new Date()) +
                ((message.length == 0) ? "" : " " + String.valueOf(Arrays.toString(message))));
    }

    private static JavaLangAccess  javaLangAccess = SharedSecrets.getJavaLangAccess();
    public static StackTraceElement getStackTraceElement(int depth, boolean fromEnd)
    {
        Throwable throwable = new Throwable();
        int maxDepth = javaLangAccess.getStackTraceDepth(throwable);
        if (fromEnd)
            depth = maxDepth - depth - 1;
        else
            depth += 1; //for this method
        if (depth < 0 || depth >= maxDepth) return null;
        else
            return javaLangAccess.getStackTraceElement(new Throwable(), depth);
    }
}