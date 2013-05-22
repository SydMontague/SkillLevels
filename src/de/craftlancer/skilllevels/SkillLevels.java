package de.craftlancer.skilllevels;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import de.craftlancer.currencyhandler.CurrencyHandler;
import de.craftlancer.skilllevels.handlers.SkillExpHandler;
import de.craftlancer.skilllevels.handlers.SkillLevelHandler;
import de.craftlancer.skilllevels.handlers.SkillPointHandler;

//TODO look for a better name
public class SkillLevels extends JavaPlugin implements Listener
{
    private FileConfiguration config;
    private FileConfiguration pconfig;
    private File pfile;
    private Map<String, LevelSystem> levelMap = new HashMap<String, LevelSystem>();
    
    @Override
    public void onEnable()
    {
        loadConfig();
        loadPlayers();
        
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new LevelListener(this), this);
        
        if (config.getBoolean("general.useCurrencyHandler", false) && getServer().getPluginManager().getPlugin("CurrencyHandler") != null)
            for (LevelSystem ls : levelMap.values())
            {
                CurrencyHandler.registerCurrency(ls.getLevelKey(), new SkillLevelHandler(ls));
                CurrencyHandler.registerCurrency(ls.getLevelKey(), new SkillPointHandler(ls));
                CurrencyHandler.registerCurrency(ls.getLevelKey(), new SkillExpHandler(ls));
            }
    }
    
    @Override
    public void onDisable()
    {
        for (Player p : getServer().getOnlinePlayers())
            savePlayer(p.getName());
    }
    
    public void handleAction(LevelAction action, String name, String player)
    {
        handleAction(action, name, 1, player);
    }
    
    public void handleAction(LevelAction action, String name, int amount, String player)
    {
        for (LevelSystem ls : levelMap.values())
            ls.handleAction(action, name, amount, player);
    }
    
    public Map<String, LevelSystem> getLevelSystems()
    {
        return levelMap;
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e)
    {
        savePlayer(e.getPlayer().getName());
    }
    
    public void loadConfig()
    {
        if (!new File(getDataFolder().getPath() + File.separatorChar + "config.yml").exists())
            saveDefaultConfig();
        
        reloadConfig();
        config = getConfig();
        
        for (String key : config.getConfigurationSection("systems").getKeys(false))
        {
            String name = config.getString("systems." + key + ".name");
            String formula = config.getString("systems." + key + ".forumla");
            int ppl = config.getInt("systsms." + key + ".pointsperlevel");
            int maxlevel = config.getInt("systsms." + key + ".maxlevel");
            
            String levelKey = config.getString("systems." + key + ".levelKey");
            String expKey = config.getString("systems." + key + ".expKey");
            String pointKey = config.getString("systems." + key + ".pointKey");
            
            String levelName = config.getString("systems." + key + ".levelName");
            String expName = config.getString("systems." + key + ".expName");
            String pointName = config.getString("systems." + key + ".pointName");
            
            Map<LevelAction, Map<String, Integer>> helpMap = new HashMap<LevelAction, Map<String, Integer>>();
            
            for (String action : config.getConfigurationSection("systems." + key + ".actions").getKeys(false))
            {
                Map<String, Integer> xpMap = new HashMap<String, Integer>();
                for (String value : config.getConfigurationSection("systems." + key + ".actions." + action).getKeys(false))
                    xpMap.put(value, config.getInt("systems." + key + ".actions." + action + "." + value));
                
                helpMap.put(LevelAction.valueOf(action), xpMap);
            }
            
            levelMap.put(key, new LevelSystem(name, ppl, maxlevel, formula, helpMap, levelName, levelKey, pointName, pointKey, expName, expKey));
        }
    }
    
    private void loadPlayers()
    {
        pfile = new File(getDataFolder(), "players.yml");
        pconfig = YamlConfiguration.loadConfiguration(pfile);
        
        for (String key : pconfig.getKeys(false))
            for (String system : pconfig.getConfigurationSection(key).getKeys(false))
                if (levelMap.containsKey(system))
                    levelMap.get(system).addLevelPlayer(key, pconfig.getInt(key + "." + system + ".exp"), pconfig.getInt(key + "." + system + ".usedskillp"));
    }
    
    private void savePlayer(String p)
    {
        for (Entry<String, LevelSystem> system : levelMap.entrySet())
        {
            LevelSystem ls = system.getValue();
            pconfig.set(p + "." + system.getKey() + ".exp", ls.getExp(p));
            pconfig.set(p + "." + system.getKey() + ".usedskillp", ls.getUsedPoints(p));
        }
        
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
