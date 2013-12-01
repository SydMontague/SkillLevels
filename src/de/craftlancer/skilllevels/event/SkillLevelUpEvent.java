package de.craftlancer.skilllevels.event;

import org.bukkit.event.HandlerList;

import de.craftlancer.skilllevels.LevelSystem;

public class SkillLevelUpEvent extends SkillLevelsEvent
{
    private static final HandlerList handlers = new HandlerList();
    private int oldlevel;
    private int newlevel;
    
    public SkillLevelUpEvent(int oldlevel, int newlevel, String user, boolean isPlayer, LevelSystem levelSystem)
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
