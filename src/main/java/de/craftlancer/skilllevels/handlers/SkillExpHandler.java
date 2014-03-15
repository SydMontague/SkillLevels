package de.craftlancer.skilllevels.handlers;

import org.bukkit.entity.Player;

import de.craftlancer.currencyhandler.Handler;
import de.craftlancer.skilllevels.LevelSystem;
import de.craftlancer.skilllevels.LevelUser;

public class SkillExpHandler implements Handler<Object, Integer>
{
    private LevelSystem system;
    
    public SkillExpHandler(LevelSystem system)
    {
        this.system = system;
    }
    
    @Override
    public boolean hasCurrency(Object p, Integer amount)
    {
        return getUser(p).getExp() >= amount;
    }
    
    @Override
    public void withdrawCurrency(Object p, Integer amount)
    {
        getUser(p).revokeExp(amount);
    }
    
    @Override
    public void giveCurrency(Object p, Integer amount)
    {
        getUser(p).addExp(amount);
    }
    
    @Override
    public void setCurrency(Object p, Integer amount)
    {
        getUser(p).setExp(amount);
    }
    
    @Override
    public String getFormatedString(Integer value)
    {
        return value.toString() + " " + getCurrencyName();
    }
    
    @Override
    public String getCurrencyName()
    {
        return system.getExpName();
    }
    
    @Override
    public boolean checkInputObject(Object obj)
    {
        return (obj instanceof Integer);
    }
    
    @Override
    public boolean checkInputHolder(Object obj)
    {
        if (obj instanceof Player)
            return system.hasUser((Player) obj);
        
        return system.hasUser(obj.toString());
    }
    
    private LevelUser getUser(Object obj)
    {
        if (obj instanceof Player)
            return system.getUser((Player) obj);
        
        return system.getUser(obj.toString());
    }
}
