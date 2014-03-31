package de.craftlancer.skilllevels.handlers;

import de.craftlancer.skilllevels.LevelSystem;

public class SkillExpHandler extends SkillHandler
{
    public SkillExpHandler(LevelSystem system)
    {
        super(system);
    }
    
    @Override
    public boolean hasCurrency(Object user, Integer amount)
    {
        return getUser(user).getExp() >= amount;
    }
    
    @Override
    public void withdrawCurrency(Object user, Integer amount)
    {
        getUser(user).revokeExp(amount);
    }
    
    @Override
    public void giveCurrency(Object user, Integer amount)
    {
        getUser(user).addExp(amount);
    }
    
    @Override
    public void setCurrency(Object user, Integer amount)
    {
        getUser(user).setExp(amount);
    }
    
    @Override
    public String getFormatedString(Integer value)
    {
        return value.toString() + " " + getCurrencyName();
    }
    
    @Override
    public String getCurrencyName()
    {
        return getLevelSystem().getExpName();
    }
}
