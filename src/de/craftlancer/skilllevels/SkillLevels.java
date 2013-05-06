package de.craftlancer.skilllevels;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class SkillLevels extends JavaPlugin
{
    private FileConfiguration config;
    private FileConfiguration pconfig;
    
    @Override
    public void onEnable()
    {
        config = getConfig();
        pconfig = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "players.yml"));
    }
    
    @Override
    public void onDisable()
    {
        
    }
}
