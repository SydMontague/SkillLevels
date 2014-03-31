package de.craftlancer.skilllevels;

import java.util.UUID;

public class LevelUser
{
    private UUID uuid;
    private LevelSystem system;
    private int exp;
    private int usedpoints;
    
    public LevelUser(int exp, int usedpoints, UUID uuid, LevelSystem system)
    {
        this.system = system;
        this.exp = exp;
        this.usedpoints = usedpoints;
        this.uuid = uuid;
    }
    
    public int getExp()
    {
        return exp;
    }
    
    public void setExp(int i)
    {
        exp = i;
    }
    
    public void addExp(int i)
    {
        exp += i;
    }
    
    public int getUsedPoints()
    {
        return usedpoints;
    }
    
    public void setUsedPoints(int i)
    {
        usedpoints = i;
    }
    
    public void addUsedPoints(int i)
    {
        usedpoints += i;
    }
    
    public void revokeExp(Integer i)
    {
        exp -= i;
    }
    
    public void revokeUsedPoints(Integer i)
    {
        usedpoints -= i;
    }
    
    public UUID getUUID()
    {
        return uuid;
    }
    
    public int getLevel()
    {
        return system.getLevel(getExp());
    }
    
    public void addLevel(int level)
    {
        int init = getLevel();
        addExp(system.getExpAtLevel(init + level) - system.getExpAtLevel(init));
    }
    
    public void revokeLevel(int level)
    {
        int init = getLevel();
        revokeExp(system.getExpAtLevel(init) - system.getExpAtLevel(init - level));
    }
    
    public void setLevel(int level)
    {
        setExp(system.getExpAtLevel(level));
    }
    
    public int getPoints()
    {
        return getLevel() * system.getPointsPerLevel() - getUsedPoints();
    }
    
    public void save()
    {
        PlayerDataHandler.getInstance().set(uuid, system, "exp", getExp());
        PlayerDataHandler.getInstance().set(uuid, system, "usedskillp", getUsedPoints());
    }
}
