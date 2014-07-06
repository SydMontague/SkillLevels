package de.craftlancer.skilllevels;

import java.util.UUID;

/*
 * act as interface between users and their SkillLevels representation
 * 
 * provide Provider specific events (e.g. PlayerProvider the current ones, TownyProvider things like "memberjoin" or "memberleft")
 * 
 */
public interface LevelableProvider
{
    public String getPrefix();
    
    public UUID getUser(String user);
    
    public boolean hasUser(String user);
}
