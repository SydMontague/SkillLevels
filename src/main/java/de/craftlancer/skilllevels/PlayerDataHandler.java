package de.craftlancer.skilllevels;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class PlayerDataHandler
{
    private SkillLevels plugin;
    private static PlayerDataHandler instance;
    private FileConfiguration pconfig;
    private File pfile;
    
    private PlayerDataHandler()
    {
        instance = this;
        plugin = SkillLevels.getInstance();
        
        if (plugin == null)
            throw new RuntimeException("Tried to initialize PlayerDataHandler without valid SkillLevels instance. This can be caused by a external plugin trying to access this class without adding the right dependency to plugin.yml!");
    }
    
    protected void loadUsers()
    {
        pfile = new File(plugin.getDataFolder(), "users.yml");
        pconfig = YamlConfiguration.loadConfiguration(pfile);
        
        for (String key : pconfig.getKeys(false))
            for (String system : pconfig.getConfigurationSection(key).getKeys(false))
                if (plugin.hasLevelSystem(system))
                    plugin.getLevelSystem(system).addUser(UUID.fromString(key), pconfig.getInt(key + "." + system + ".exp"), pconfig.getInt(key + "." + system + ".usedskillp"));
    }
    
    @SuppressWarnings("unused")
    protected static PlayerDataHandler getInstance()
    {
        if (instance == null)
            new PlayerDataHandler();
        
        return instance;
    }
    
    public void set(UUID user, LevelSystem system, String field, Object value)
    {
        pconfig.set(user.toString() + "." + system.getSystemKey() + "." + field, value);
    }
    
    public void save()
    {
        try
        {
            pconfig.save(pfile);
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
    }
}
