package de.craftlancer.skilllevels;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

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
    
    @SuppressWarnings("deprecation")
    protected void loadUsers()
    {
        pfile = new File(plugin.getDataFolder(), "users.yml");
        pconfig = YamlConfiguration.loadConfiguration(pfile);
        
        List<String> playersToNull = new ArrayList<String>(); // UUID update procedure
        
        for (String key : pconfig.getKeys(false))
            for (String system : pconfig.getConfigurationSection(key).getKeys(false))
                if (plugin.hasLevelSystem(system))
                {
                    UUID uuid = null;
                    
                    // UUID update procedure
                    try
                    {
                        uuid = UUID.fromString(key);
                    }
                    catch (IllegalArgumentException e)
                    {
                        uuid = Bukkit.getOfflinePlayer(key).getUniqueId();
                        playersToNull.add(key);
                    }
                    
                    plugin.getLevelSystem(system).addUser(uuid, pconfig.getInt(key + "." + system + ".exp"), pconfig.getInt(key + "." + system + ".usedskillp"));
                }
        
        // UUID update procedure
        if (!playersToNull.isEmpty())
        {
            for (String name : playersToNull)
                pconfig.set(name, null);
            
            plugin.save(false);
        }
    }
    
    protected static PlayerDataHandler getInstance()
    {
        if (instance == null)
            new PlayerDataHandler();
        
        return instance;
    }
    
    protected void set(UUID user, LevelSystem system, String field, Object value)
    {
        pconfig.set(user.toString() + "." + system.getSystemKey() + "." + field, value);
    }
    
    public void save(boolean shutdown)
    {
        if (!shutdown)
            new BukkitRunnable()
            {
                @Override
                public void run()
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
            }.runTaskAsynchronously(plugin);
        else
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
