package de.craftlancer.skilllevels.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.craftlancer.skilllevels.LevelSystem;

public class SkillLevelsEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();
    private String user;
    private boolean isPlayer;
    private LevelSystem system;
    
    public SkillLevelsEvent(String user, boolean isPlayer, LevelSystem system)
    {
        this.user = user;
        this.isPlayer = isPlayer;
        this.system = system;
    }
    
    public String getUser()
    {
        return user;
    }
    
    public boolean isPlayer()
    {
        return isPlayer;
    }
    
    public LevelSystem getLevelSystem()
    {
        return system;
    }
    
    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }
    
}
