package de.craftlancer.skilllevels;

public class LevelPlayer
{
    private int exp;
    private int usedpoints;
    
    public LevelPlayer(int exp, int usedpoints)
    {
        this.exp = exp;
        this.usedpoints = usedpoints;
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
}
