package de.craftlancer.skilllevels.handlers;

import java.util.UUID;

import org.bukkit.entity.Player;

import de.craftlancer.currencyhandler.Handler;
import de.craftlancer.skilllevels.LevelSystem;
import de.craftlancer.skilllevels.LevelUser;
import de.craftlancer.skilllevels.Levelable;

public abstract class SkillHandler implements Handler<Object, Integer>
{
    private LevelSystem system;
    
    public SkillHandler(LevelSystem system)
    {
        this.system = system;
    }
    
    public LevelSystem getLevelSystem()
    {
        return system;
    }
    
    @Override
    public final boolean checkInputObject(Object obj)
    {
        return (obj instanceof Integer);
    }
    
    @Override
    public final boolean checkInputHolder(Object obj)
    {
        if (obj instanceof Player)
            return system.hasUser((Player) obj);
        
        if (obj instanceof Levelable)
            return system.hasUser((Levelable) obj);
        
        if (obj instanceof UUID)
            return system.hasUser((UUID) obj);
        
        try
        {
            return system.hasUser(UUID.fromString(obj.toString()));
        }
        catch (IllegalArgumentException e)
        {
            return false;
        }
    }
    
    protected final LevelUser getUser(Object obj)
    {
        if (obj instanceof Player)
            return system.getUser((Player) obj);
        
        if (obj instanceof Levelable)
            return system.getUser((Levelable) obj);
        
        if (obj instanceof UUID)
            return system.getUser((UUID) obj);
        
        try
        {
            return system.getUser(UUID.fromString(obj.toString()));
        }
        catch (IllegalArgumentException e)
        {
            return null;
        }
    }
    
}
