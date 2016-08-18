package me.mayoralty.alternative.util;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

public class AUtil
{

    public static List<String> IMPOSTORS = new ArrayList<>();

    public static void bcastAction(String name, String message, boolean isRed)
    {
        Bukkit.broadcastMessage((isRed ? ChatColor.RED : ChatColor.AQUA) + name + message);
    }

    public static void bcastMessage(String message, ChatColor color)
    {
        Bukkit.broadcastMessage(color + message);
    }

    public static String colour(String string)
    {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
