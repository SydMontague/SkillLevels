package de.craftlancer.skilllevels.handlers;

import org.bukkit.entity.Player;

import de.craftlancer.currencyhandler.Handler;
import de.craftlancer.skilllevels.LevelSystem;

public class SkillPointHandler implements Handler<Integer>
{
    private LevelSystem system;
    
    public SkillPointHandler(LevelSystem system)
    {
        this.system = system;
    }
    
    @Override
    public boolean hasCurrency(Player p, Integer amount)
    {
        return system.getPoints(p) >= amount;
    }
    
    @Override
    public void withdrawCurrency(Player p, Integer amount)
    {
        system.addUsedPoints(amount, p);
    }
    
    @Override
    public void giveCurrency(Player p, Integer amount)
    {
        system.revokeUsedPoints(amount, p);
    }
    
    @Override
    public void setCurrency(Player arg0, Integer arg1)
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
}
