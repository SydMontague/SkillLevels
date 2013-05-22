package de.craftlancer.skilllevels.handlers;

import org.bukkit.entity.Player;

import de.craftlancer.currencyhandler.Handler;
import de.craftlancer.skilllevels.LevelSystem;

public class SkillExpHandler implements Handler<Integer>
{
    LevelSystem system;
    
    public SkillExpHandler(LevelSystem system)
    {
        this.system = system;
    }
    
    @Override
    public boolean hasCurrency(Player p, Integer amount)
    {
        return system.getExp(p) >= amount;
    }
    
    @Override
    public void withdrawCurrency(Player p, Integer amount)
    {
        system.revokeExp(amount, p);
    }
    
    @Override
    public void giveCurrency(Player p, Integer amount)
    {
        system.addExp(amount, p);
    }
    
    @Override
    public void setCurrency(Player p, Integer amount)
    {
        system.setExp(amount, p);
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
}
