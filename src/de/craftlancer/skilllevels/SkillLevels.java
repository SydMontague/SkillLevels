package de.craftlancer.skilllevels;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SkillLevels extends JavaPlugin
{
    public static int skillsperlevel;
    
    private FileConfiguration config;
    private FileConfiguration pconfig;
    private Map<String, SkillPlayer> playerMap = new HashMap<String, SkillPlayer>();
    
    private Map<EntityType, Integer> killMap = new HashMap<EntityType, Integer>();
    
    @Override
    public void onEnable()
    {
        if (!new File(getDataFolder().getPath() + File.separatorChar + "config.yml").exists())
            saveDefaultConfig();
        
        config = getConfig();
        
        skillsperlevel = config.getInt("general.pointsperlevel", 5);
       
        for (String key : config.getConfigurationSection("exp.mobkill").getKeys(false))
            killMap.put(EntityType.fromName(key), config.getInt("exp.mobkill." + key));
        
        pconfig = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "players.yml"));
             
        
        for (String key : pconfig.getKeys(false))
            playerMap.put(key.toLowerCase(), new SkillPlayer(pconfig.getInt(key + ".exp"), pconfig.getInt(key + ".usedskillp")));
    }
    
    @Override
    public void onDisable()
    {
        
    }
    
    public SkillPlayer getSkillPlayer(Player p)
    {
        return playerMap.get(p.getName());
    }
    
    public int getMobKillExp(EntityType type)
    {
        return killMap.get(type);
    }
}
