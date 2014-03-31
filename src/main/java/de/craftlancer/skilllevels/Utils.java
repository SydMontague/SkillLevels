package de.craftlancer.skilllevels;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;

public class Utils
{
    /**
     * Check if a String array contains a String
     * The check is case sensitive
     *
     * @param array the array
     * @param string the String
     * @return true if the array contains the string, false if not
     */
    public static boolean arrayContains(String[] array, String string)
    {
        if (array != null && array.length != 0)
            for (String value : array)
                if (value.equals(string))
                    return true;
        
        return false;
    }
    
    /**
     * Get all values of a String Collection which start with a given String
     *
     * @param value the given String
     * @param list the Collection
     * @return a List of all matches
     */
    public static List<String> getMatches(String value, Collection<String> list)
    {
        List<String> result = new LinkedList<String>();
        
        for (String str : list)
            if (str.startsWith(value))
                result.add(str);
        
        return result;
    }
    
    /**
     * Get all values of a String array which start with a given String
     *
     * @param value the given String
     * @param list the array
     * @return a List of all matches
     */
    public static List<String> getMatches(String value, String[] list)
    {
        List<String> result = new LinkedList<String>();
        
        for (String str : list)
            if (str.startsWith(value))
                result.add(str);
        
        return result;
    }
    
    public static void debug(String s)
    {
        Bukkit.getLogger().info("[Debug] " + s);
    }
}
