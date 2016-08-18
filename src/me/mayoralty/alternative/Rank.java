package me.mayoralty.alternative;

import me.mayoralty.alternative.util.AUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Rank
{

    public enum Level
    {
        IMPOSTOR("Imposotor", "Imp", "an", ChatColor.YELLOW),
        GUEST("Guest", "Guest", "a", ChatColor.WHITE),
        OPERATOR("Operator", "Op", "an", ChatColor.GRAY),
        SUPER_ADMIN("Super Admin", "SA", "a", ChatColor.AQUA),
        TELNET_ADMIN("Super Telnet Admin", "STA", "a", ChatColor.DARK_GREEN),
        SENIOR_ADMIN("Senior Admin", "SrA", "a", ChatColor.LIGHT_PURPLE),
        CONSOLE(null, "Console", "the", ChatColor.DARK_PURPLE);

        private final String name;
        private final String prefix;
        private final String deter;
        private final ChatColor color;

        private Level(String name, String prefix, String deter, ChatColor color)
        {
            this.name = name;
            this.deter = deter;
            this.color = color;
            this.prefix = ChatColor.DARK_GRAY + "[" + color + prefix + ChatColor.DARK_GRAY + "] " + color;
        }

        public int getLevel()
        {
            return this.ordinal();
        }

        public boolean isAtLeast(Level rank)
        {
            return getLevel() >= rank.getLevel();
        }

        public String getLoginMessage()
        {
            return deter + " " + color + name;
        }

        public String getDefaultTag()
        {
            return prefix;
        }
    }

    public static Level getRank(Player player)
    {
        if (player.isOp())
        {
            return Level.OPERATOR;
        }

        if (!(player instanceof Player))
        {
            if (player.getName().equalsIgnoreCase("CONSOLE"))
            {
                return Level.CONSOLE;
            }
            else
            {
                OfflinePlayer offline = Bukkit.getOfflinePlayer(player.getName().replaceAll("[^A-Za-z0-9]", ""));
                if (offline == null)
                {
                    return Level.TELNET_ADMIN;
                }

                for (Level rank : Level.values())
                {
                    if (Core.plugin.admins.getConfig().getString(offline.getUniqueId().toString() + ".rank").equalsIgnoreCase(rank.name.replace(" ", "_")))
                    {
                        return rank;
                    }
                }
            }
        }

        try
        {
            for (Level rank : Level.values())
            {
                if (Core.plugin.admins.getConfig().getString(((Player) player).getUniqueId().toString() + ".rank").equalsIgnoreCase(rank.name.replace(" ", "_")))
                {
                    return rank;
                }
            }
        }
        catch (Exception none)
        {
        }

        if (AUtil.IMPOSTORS.contains(player.getName()))
        {
            return Level.IMPOSTOR;
        }

        return Level.GUEST;
    }

    public static Level getRank(CommandSender sender)
    {
        return getRank((Player) sender);
    }

    public static Level getRankFromIp(String ip)
    {
        for (String uuid : Core.plugin.admins.getConfig().getConfigurationSection("").getKeys(false))
        {
            if (Core.plugin.admins.getConfig().getStringList(uuid + ".ips").contains(ip))
            {
                for (Level rank : Level.values())
                {
                    if (Core.plugin.admins.getConfig().getString(uuid + ".rank").equalsIgnoreCase(rank.name.replace(" ", "_")))
                    {
                        return rank;
                    }
                }
            }
        }
        return Level.GUEST;
    }

    public static Level getRankFromString(String rank)
    {
        try
        {
            for (Level rank1 : Level.values())
            {
                if (rank.equalsIgnoreCase(rank1.name.replace(" ", "_")))
                {
                    return rank1;
                }
            }
        }
        catch (Exception none)
        {
        }
        return null;
    }
}
