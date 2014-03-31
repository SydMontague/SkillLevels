package de.craftlancer.skilllevels.handlers;

import de.craftlancer.skilllevels.LevelSystem;

public class SkillPointHandler extends SkillHandler
{
    public SkillPointHandler(LevelSystem system)
    {
        super(system);
    }
    
    @Override
    public boolean hasCurrency(Object user, Integer amount)
    {
        return getUser(user).getPoints() >= amount;
    }
    
    @Override
    public void withdrawCurrency(Object user, Integer amount)
    {
        getUser(user).addUsedPoints(amount);
    }
    
    @Override
    public void giveCurrency(Object user, Integer amount)
    {
        getUser(user).revokeUsedPoints(amount);
    }
    
    @Override
    public void setCurrency(Object holder, Integer amount)
    {
        throw new UnsupportedOperationException("SkillPointHandler does not support setCurrency()!");
    }
    
    @Override
    public String getFormatedString(Integer value)
    {
        return value.toString() + " " + getCurrencyName();
    }
    
    @Override
    public String getCurrencyName()
    {
        return getLevelSystem().getPointName();
    }
}
