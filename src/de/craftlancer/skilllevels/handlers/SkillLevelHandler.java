package de.craftlancer.skilllevels.handlers;

import org.bukkit.entity.Player;

import de.craftlancer.currencyhandler.Handler;
import de.craftlancer.skilllevels.LevelSystem;

public class SkillLevelHandler implements Handler<Integer>
{
    private LevelSystem system;
    
    public SkillLevelHandler(LevelSystem system)
    {
        this.system = system;
    }
    
    @Override
    public boolean hasCurrency(Player p, Integer amount)
    {
        return system.getLevel(p) >= amount;
    }
    
    @Override
    public void withdrawCurrency(Player p, Integer amount)
    {
        int level = system.getLevel(p);
        system.revokeExp(system.getExpAtLevel(level) - system.getExpAtLevel(level - amount), p);
    }
    
    @Override
    public void giveCurrency(Player p, Integer amount)
    {
        int level = system.getLevel(p);
        system.addExp(system.getExpAtLevel(level + amount) - system.getExpAtLevel(level), p);
    }
    
    @Override
    public void setCurrency(Player p, Integer amount)
    {
        system.setExp(system.getExpAtLevel(amount), p);
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
}
