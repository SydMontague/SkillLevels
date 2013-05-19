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
        // TODO Auto-generated method stub
        return false;
    }
    
}
