package de.craftlancer.skilllevels;

import org.bukkit.entity.Player;

import de.craftlancer.currencyhandler.Handler;

public class SkillpointHandler implements Handler<Integer>
{
    SkillLevels plugin;
    String name;
    
    public SkillpointHandler(SkillLevels plugin, String name)
    {
        this.plugin = plugin;
        this.name = name;
    }

    @Override
    public boolean hasCurrency(Player p, Integer amount)
    {
        return plugin.getSkillPlayer(p).getSkillPoints() >= amount;
    }

    @Override
    public void withdrawCurrency(Player p, Integer amount)
    {
        plugin.getSkillPlayer(p).withdrawSkillPoints(amount);
    }

    @Override
    public void giveCurrency(Player p, Integer amount)
    {
        plugin.getSkillPlayer(p).addSkillPoints(amount);        
    }
    
    @Override
    public String getCurrencyName()
    {
        return name;
    }

    @Override
    public boolean checkInputObject(Object obj)
    {
        return obj instanceof Integer;
    }

    @Override
    public String getFormatedString(Object value)
    {
        return ((Integer) value).toString() + " " + getCurrencyName();
    }    
}
