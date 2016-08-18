package me.mayoralty.alternative;

import me.mayoralty.alternative.util.ALog;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin
{

    public static Core plugin;
    //
    public AConfig config;
    public AConfig admins;

    @Override
    public void onLoad()
    {
        Core.plugin = this;
        //
        config = new AConfig(plugin, "config.yml");
        admins = new AConfig(plugin, "admins.yml");
    }

    @Override
    public void onEnable()
    {
        Core.plugin = this;
        //
        config.load();
        admins.load();
        //
        ALog.info("Alternative plugin has been enabled.");
    }

    @Override
    public void onDisable()
    {
        config.save();
        admins.save();
        //
        Core.plugin = null;
        ALog.info("Alternative plugin has been disabled.");
    }
}
