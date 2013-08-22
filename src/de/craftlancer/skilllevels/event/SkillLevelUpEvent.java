package de.craftlancer.skilllevels.event;

import org.bukkit.event.HandlerList;

import de.craftlancer.skilllevels.LevelSystem;

public class SkillLevelUpEvent extends SkillLevelsEvent
{
    private static final HandlerList handlers = new HandlerList();
    private int oldlevel;
    private int newlevel;
    
    public SkillLevelUpEvent(int initlevel, int newlevel, String user, boolean isPlayer, LevelSystem levelSystem)
    {
        super(user, isPlayer, levelSystem);
        this.oldlevel = initlevel;
        this.newlevel = newlevel;
    }
    
    public int getOldlevel()
    {
        return oldlevel;
    }
    
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
