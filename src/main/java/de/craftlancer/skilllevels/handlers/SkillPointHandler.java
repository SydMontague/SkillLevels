package de.craftlancer.skilllevels.handlers;

import org.bukkit.entity.Player;

import de.craftlancer.currencyhandler.Handler;
import de.craftlancer.skilllevels.LevelSystem;

public class SkillPointHandler implements Handler<Object, Integer>
{
    private LevelSystem system;
    
    public SkillPointHandler(LevelSystem system)
    {
        this.system = system;
    }
    
    @Override
    public boolean hasCurrency(Object p, Integer amount)
    {
        return system.getPoints(getUserName(p)) >= amount;
    }
    
    @Override
    public void withdrawCurrency(Object p, Integer amount)
    {
        system.addUsedPoints(amount, getUserName(p));
    }
    
    @Override
    public void giveCurrency(Object p, Integer amount)
    {
        system.revokeUsedPoints(amount, getUserName(p));
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
        return system.getPointName();
    }
    
    @Override
    public boolean checkInputObject(Object obj)
    {
        return obj instanceof Integer;
    }
    
    @Override
    public boolean checkInputHolder(Object obj)
    {
        if (obj instanceof Player)
            return system.hasUser((Player) obj);
        
        return system.hasUser(obj.toString());
    }
    
    private String getUserName(Object obj)
    {
        if (obj instanceof Player)
            return ((Player) obj).getName();
        
        return obj.toString();
    }
}
