package de.craftlancer.skilllevels.event;

import org.bukkit.event.Event;

import de.craftlancer.skilllevels.LevelSystem;
import de.craftlancer.skilllevels.LevelUser;

public abstract class SkillLevelsEvent extends Event
{
    private LevelUser user;
    private boolean isPlayer;
    private LevelSystem system;
    
    public SkillLevelsEvent(LevelUser user, boolean isPlayer, LevelSystem system)
    {
        this.user = user;
        this.isPlayer = isPlayer;
        this.system = system;
    }
    
    public LevelUser getUser()
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
