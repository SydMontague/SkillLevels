package de.craftlancer.skilllevels.handlers;

import org.bukkit.entity.Player;

import de.craftlancer.currencyhandler.Handler;
import de.craftlancer.skilllevels.LevelSystem;

public class SkillPointHandler implements Handler<Integer>
{
    LevelSystem system;
    
    public SkillPointHandler(LevelSystem system)
    {
        this.system = system;
    }
    
    @Override
    public boolean hasCurrency(Player p, Integer amount)
    {
        return system.getLevel(system.getPlayer(p).getExp()) * system.getPointsPerLevel() - system.getPlayer(p).getUsedPoints() >= amount;
    }
    
    @Override
    public void withdrawCurrency(Player p, Integer amount)
    {
        system.getPlayer(p).addUsedPoints(amount);
    }
    
    @Override
    public void giveCurrency(Player p, Integer amount)
    {
        system.getPlayer(p).revokeUsedPoints(amount);
    }
    
    @Override
    public void setCurrency(Player arg0, Integer arg1)
    {
        throw new UnsupportedOperationException("ItemHandler does not support setCurrency()! Skipping the handler.");
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
