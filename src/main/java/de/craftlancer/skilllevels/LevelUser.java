package de.craftlancer.skilllevels;

public class LevelUser
{
    private int exp;
    private int usedpoints;
    private String name;
    
    public LevelUser(int exp, int usedpoints, String name)
    {
        this.exp = exp;
        this.usedpoints = usedpoints;
        this.name = name;
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
    
    public String getName()
    {
        return name;
    }
}
