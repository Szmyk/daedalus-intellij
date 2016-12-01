package org.avallach.commons;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ResourceUtils
{
    public static String readFile(Class ownerClass, String fileName) throws IOException
    {
        String resourcePath = ownerClass.getSimpleName() + "/" + fileName;
        try(InputStream inputStream = ownerClass.getResourceAsStream(resourcePath);
            Scanner scanner = new Scanner(inputStream))
        {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }
}
