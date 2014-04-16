package de.craftlancer.skilllevels.handlers;

import de.craftlancer.skilllevels.LevelSystem;

public class SkillPointHandler extends SkillHandler
{
    public SkillPointHandler(LevelSystem system)
    {
        super(system);
    }
    
    @Override
    public boolean hasCurrency(Object user, Object amount)
    {
        if (!checkInputHolder(user))
            return false;
        
        if (!checkInputObject(amount))
            return false;
        
        return getUser(user).getPoints() >= (Integer) amount;
    }
    
    @Override
    public void withdrawCurrency(Object user, Object amount)
    {
        if (!checkInputHolder(user))
            return;
        
        if (!checkInputObject(amount))
            return;
        
        getUser(user).addUsedPoints((Integer) amount);
    }
    
    @Override
    public void giveCurrency(Object user, Object amount)
    {
        if (!checkInputHolder(user))
            return;
        
        if (!checkInputObject(amount))
            return;
        
        getUser(user).revokeUsedPoints((Integer) amount);
    }
    
    @Override
    public void setCurrency(Object user, Object amount)
    {
        throw new UnsupportedOperationException("SkillPointHandler does not support setCurrency()!");
    }
    
    @Override
    public String getFormatedString(Object value)
    {
        if (!checkInputObject(value))
            return "INVALID INPUT OBJECT";
        
        return value.toString() + " " + getCurrencyName();
    }
    
    @Override
    public String getCurrencyName()
    {
        return getLevelSystem().getPointName();
    }
}
