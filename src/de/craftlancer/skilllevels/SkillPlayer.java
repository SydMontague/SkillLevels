package de.craftlancer.skilllevels;

public class SkillPlayer
{
    private int exp;
    private int usedpoints;
    
    public SkillPlayer(int exp, int pointbalance)
    {
        this.exp = exp;
        this.usedpoints = pointbalance;
    }
    
    public int getLevel()
    {
        return getLevel(exp);
    }
    
    public void withdrawExp(int xp)
    {
        if(xp == 0)
            return;
        
        int oldlevel = getLevel(exp);        
        exp -= xp;
        
        if(oldlevel > getLevel(exp))
            notifyLevelDown();
    }

    public void addExp(int xp)
    {
        if(xp == 0)
            return;
        
        int oldlevel = getLevel(exp);        
        exp += xp;
        
        if(oldlevel < getLevel(exp))
            notifyLevelUp();
    }
    
    private void notifyLevelUp()
    {
        // TODO Auto-generated method stub
        
    }    
    
    private void notifyLevelDown()
    {
        // TODO Auto-generated method stub
        
    }

    public int getExp()
    {
        return exp;
    }
    
    public void addSkillPoints(int i)
    {
        usedpoints -= i;
    }
    
    public void withdrawSkillPoints(int i)
    {
        usedpoints += i;
    }
    
    public int getSkillPoints()
    {
        return getLevel() * SkillLevels.skillsperlevel - usedpoints;
    }
    
    public int getLevel(int i)
    {
        //TODO formula
        return 0;
    }
}
