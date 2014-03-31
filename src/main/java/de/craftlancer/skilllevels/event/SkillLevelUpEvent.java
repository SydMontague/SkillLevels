package de.craftlancer.skilllevels.event;

import org.bukkit.event.HandlerList;

import de.craftlancer.skilllevels.LevelSystem;
import de.craftlancer.skilllevels.LevelUser;

public class SkillLevelUpEvent extends SkillLevelsEvent
{
    private static final HandlerList handlers = new HandlerList();
    private int oldlevel;
    private int newlevel;
    
    public SkillLevelUpEvent(LevelUser user, boolean isPlayer, LevelSystem levelSystem, int oldlevel, int newlevel)
    {
        super(user, isPlayer, levelSystem);
        this.oldlevel = oldlevel;
        this.newlevel = newlevel;
    }
    
    public int getOldLevel()
    {
        return oldlevel;
    }
    
    @Deprecated
    public int getOldlevel()
    {
        return oldlevel;
    }
    
    public int getNewLevel()
    {
        return newlevel;
    }
    
    @Deprecated
    public int getNewlevel()
    {
        return newlevel;
    }
    
    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }
    
    public static HandlerList getHandlerList()
    {
        return handlers;
    }
}
