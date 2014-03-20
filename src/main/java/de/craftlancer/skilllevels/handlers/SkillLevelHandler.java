package de.craftlancer.skilllevels.handlers;

import org.bukkit.entity.Player;

import de.craftlancer.currencyhandler.Handler;
import de.craftlancer.skilllevels.LevelSystem;

public class SkillLevelHandler implements Handler<Object, Integer>
{
    private LevelSystem system;
    
    public SkillLevelHandler(LevelSystem system)
    {
        this.system = system;
    }
    
    @Override
    public boolean hasCurrency(Object p, Integer amount)
    {
        return system.getLevel(getUserName(p)) >= amount;
    }
    
    @Override
    public void withdrawCurrency(Object p, Integer amount)
    {
        int level = system.getLevel(getUserName(p));
        system.revokeExp(system.getExpAtLevel(level) - system.getExpAtLevel(level - amount), getUserName(p));
    }
    
    @Override
    public void giveCurrency(Object p, Integer amount)
    {
        int level = system.getLevel(getUserName(p));
        system.addExp(system.getExpAtLevel(level + amount) - system.getExpAtLevel(level), getUserName(p));
    }
    
    @Override
    public void setCurrency(Object p, Integer amount)
    {
        system.setExp(system.getExpAtLevel(amount), getUserName(p));
    }
    
    @Override
    public String getFormatedString(Integer value)
    {
        return value.toString() + " " + getCurrencyName();
    }
    
    @Override
    public String getCurrencyName()
    {
        return system.getLevelName();
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
    
    private static String getUserName(Object obj)
    {
        if (obj instanceof Player)
            return ((Player) obj).getName();
        
        return obj.toString();
    }
}
