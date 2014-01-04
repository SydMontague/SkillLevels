package de.craftlancer.skilllevels.event;

import org.bukkit.event.Event;

import de.craftlancer.skilllevels.LevelSystem;

public abstract class SkillLevelsEvent extends Event
{
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
}
