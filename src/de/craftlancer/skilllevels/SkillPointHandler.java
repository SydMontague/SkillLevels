package de.craftlancer.skilllevels;

import org.bukkit.entity.Player;

import de.craftlancer.currencyhandler.Handler;

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
        return false; //TODO
    }

    @Override
    public void withdrawCurrency(Player p, Integer amount)
    {
        //TODO
    }

    @Override
    public void giveCurrency(Player p, Integer amount)
    {
        //TODO       
    }
    
    @Override
    public String getFormatedString(Integer value)
    {
        return value.toString() + " " + getCurrencyName();
    }   
    
    @Override
    public String getCurrencyName()
    {
        return null;//TODO
    }

    @Override
    public boolean checkInputObject(Object obj)
    {
        return obj instanceof Integer;
    }
}
