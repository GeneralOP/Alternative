package me.mayoralty.alternative.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ALog
{

    public static Logger log = Logger.getLogger("Minecraft");
    public static String pname = "[Alternative]:";

    public static void log(Level level, String message, Exception ex)
    {
        log.log(level, "{0}{1}{2}", new Object[]
        {
            pname, message, ex.toString()
        });
    }

    public static void info(String message)
    {
        log(Level.INFO, message, null);
    }

    public static void severe(String message)
    {
        log(Level.SEVERE, message, null);
    }

    public static void severe(Exception ex)
    {
        log(Level.SEVERE, null, ex);
    }

    public static void severe(String message, Exception ex)
    {
        log(Level.SEVERE, message, ex);
    }
}
