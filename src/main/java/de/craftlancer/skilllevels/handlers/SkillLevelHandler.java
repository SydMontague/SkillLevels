package de.craftlancer.skilllevels.handlers;

import de.craftlancer.skilllevels.LevelSystem;

public class SkillLevelHandler extends SkillHandler
{
    public SkillLevelHandler(LevelSystem system)
    {
        super(system);
    }
    
    @Override
    public boolean hasCurrency(Object user, Integer amount)
    {
        return getLevelSystem().getLevel(getUser(user).getExp()) >= amount;
    }
    
    @Override
    public void withdrawCurrency(Object user, Integer amount)
    {
        getUser(user).revokeLevel(amount);
    }
    
    @Override
    public void giveCurrency(Object user, Integer amount)
    {
        getUser(user).addLevel(amount);
    }
    
    @Override
    public void setCurrency(Object user, Integer amount)
    {
        getUser(user).setLevel(amount);
    }
    
    @Override
    public String getFormatedString(Integer value)
    {
        return value.toString() + " " + getCurrencyName();
    }
    
    @Override
    public String getCurrencyName()
    {
        return getLevelSystem().getLevelName();
    }
}
