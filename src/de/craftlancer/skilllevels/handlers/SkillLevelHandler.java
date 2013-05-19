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
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public void withdrawCurrency(Player p, Integer amount)
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void giveCurrency(Player p, Integer amount)
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public String getFormatedString(Integer value)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public String getCurrencyName()
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public boolean checkInputObject(Object obj)
    {
        return obj instanceof Integer;
    }
    
}
