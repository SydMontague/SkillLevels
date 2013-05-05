package de.craftlancer.skilllevels;

import org.bukkit.entity.Player;

import de.craftlancer.currencyhandler.Handler;

public class SkillpointHandler implements Handler<Integer>
{

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
    public String getCurrencyName()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean checkInputClass(Object obj)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getFormatedString(Object value)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
}
