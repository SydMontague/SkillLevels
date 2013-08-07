package de.craftlancer.skilllevels.event;

import de.craftlancer.skilllevels.LevelAction;
import de.craftlancer.skilllevels.LevelSystem;

public class SkillExpChangeEvent extends SkillLevelsEvent
{
    private int amount;
    private LevelAction action;
    
    public SkillExpChangeEvent(String user, boolean isPlayer, int amount, LevelAction action, LevelSystem levelSystem)
    {
        super(user, isPlayer, levelSystem);
        this.amount = amount;
        this.action = action;
    }
    
    public int getAmount()
    {
        return amount;
    }
    
    public LevelAction getAction()
    {
        return action;
    }
}
