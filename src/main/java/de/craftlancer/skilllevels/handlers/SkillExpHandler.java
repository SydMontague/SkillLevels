package de.craftlancer.skilllevels.handlers;

import de.craftlancer.skilllevels.LevelSystem;

public class SkillExpHandler extends SkillHandler
{
    public SkillExpHandler(LevelSystem system)
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
        
        return getUser(user).getExp() >= (Integer) amount;
    }
    
    @Override
    public void withdrawCurrency(Object user, Object amount)
    {
        if (!checkInputHolder(user))
            return;
        
        if (!checkInputObject(amount))
            return;
        
        getUser(user).revokeExp((Integer) amount);
    }
    
    @Override
    public void giveCurrency(Object user, Object amount)
    {
        if (!checkInputHolder(user))
            return;
        
        if (!checkInputObject(amount))
            return;
        
        getUser(user).addExp((Integer) amount);
    }
    
    @Override
    public void setCurrency(Object user, Object amount)
    {
        if (!checkInputHolder(user))
            return;
        
        if (!checkInputObject(amount))
            return;
        
        getUser(user).setExp((Integer) amount);
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
        return getLevelSystem().getExpName();
    }
}
