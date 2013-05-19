package de.craftlancer.skilllevels.handlers;

import org.bukkit.entity.Player;

import de.craftlancer.currencyhandler.Handler;
import de.craftlancer.skilllevels.LevelPlayer;
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
        return system.getLevel(system.getPlayer(p).getExp()) >= amount;
        
    }
    
    @Override
    public void withdrawCurrency(Player p, Integer amount)
    {
        LevelPlayer sp = system.getPlayer(p);
        int level = system.getLevel(sp.getExp());
        sp.revokeExp(system.getExpAtLevel(level) - system.getExpAtLevel(level - amount));
    }
    
    @Override
    public void giveCurrency(Player p, Integer amount)
    {
        LevelPlayer sp = system.getPlayer(p);
        int level = system.getLevel(sp.getExp());
        sp.addExp(system.getExpAtLevel(level + amount) - system.getExpAtLevel(level));
    }
    
    @Override
    public void setCurrency(Player p, Integer amount)
    {
        system.getPlayer(p).setExp(system.getExpAtLevel(amount));
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
