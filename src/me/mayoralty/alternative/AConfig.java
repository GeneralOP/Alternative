package me.mayoralty.alternative;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import me.mayoralty.alternative.util.ALog;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class AConfig
{

    private final Core plugin;
    private final String fileName;
    //
    private File configFile;
    private FileConfiguration fc;
    private File df;

    public AConfig(Core plugin, String fileName)
    {
        if (!plugin.isInitialized())
        {
            throw new IllegalArgumentException("plugin must be initialized");
        }
        this.plugin = plugin;
        this.fileName = fileName;
        try
        {
            this.df = plugin.getDataFolder();
        }
        catch (IllegalStateException ex)
        {
            ALog.severe(ex);
        }

        this.configFile = new File(df, fileName);
    }

    public void load()
    {
        fc = YamlConfiguration.loadConfiguration(configFile);
        //
        InputStream is = plugin.getResource(fileName);
        if (is != null)
        {
            YamlConfiguration yc = YamlConfiguration.loadConfiguration(is);
            fc.setDefaults(fc);
        }
    }

    public void save()
    {
        if (fc == null || configFile == null)
        {
            return;
        }
        try
        {
            plugin.getConfig().save(configFile);
        }
        catch (IOException ex)
        {
            ALog.severe("Could not save config to " + configFile.getName(), ex);
        }
    }

    public void reload()
    {
        this.load();
    }

    public FileConfiguration getConfig()
    {
        if (fc == null)
        {
            this.reload();
        }
        return fc;
    }
}
