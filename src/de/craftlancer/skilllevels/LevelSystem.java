package de.craftlancer.skilllevels;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class LevelSystem
{
    private int pointsperlevel;
    private int maxlevel;
    private String formula;
    private int[] preCalc = null;
    
    private String levelKey;
    private String expKey;
    private String pointKey;
    
    private String levelName;
    private String expName;
    private String pointName;
    
    private Map<LevelAction, Map<String, Integer>> xpperaction = new HashMap<LevelAction, Map<String, Integer>>();
    private Map<String, LevelPlayer> playerMap = new HashMap<String, LevelPlayer>();
    
    public LevelSystem(int ppl, int maxlevel, String form, Map<LevelAction, Map<String, Integer>> xpmap, String levelName, String levelKey, String pointName, String pointKey, String expName, String expKey)
    {
        this.maxlevel = maxlevel;
        
        if (maxlevel > 0)
            preCalcLevels();
        
        setPointsPerLevel(ppl);
        setFormula(form);
        xpperaction = xpmap;
        
        setLevelName(levelName);
        setPointName(pointName);
        setExpName(expName);
        setExpKey(expKey);
        setLevelKey(levelKey);
        setPointKey(pointKey);
    }
    
    private void preCalcLevels()
    {
        preCalc = new int[maxlevel + 1];
        
        for (int i = 0; i <= maxlevel; i++)
            preCalc[i] = calcExpAtLevel(i);
    }
    
    public int calcExpAtLevel(int x)
    {
        return Integer.valueOf(getMathResult(formula, x, formula));
    }
    
    private static String getMathResult(String form, double x, String completForm)
    {
        if (x <= 0)
            return "0";
        
        form = form.replace("x", String.valueOf(x));
        
        while (form.contains("(") && form.contains(")"))
        {
            int indexFirst = -1;
            int indexLast = -1;
            int countOpen = 0;
            for (int i = 0; i < form.toCharArray().length; i++)
            {
                if (form.toCharArray()[i] == '(')
                    if (indexFirst == -1)
                        indexFirst = i;
                    else
                        countOpen++;
                
                if (form.toCharArray()[i] == ')')
                    if (indexLast == -1 && countOpen == 0)
                        indexLast = i;
                    else
                        countOpen--;
            }
            
            if (indexFirst != -1 && indexLast != -1)
                form = form.replace(form.substring(indexFirst, indexLast + 1), getMathResult(form.substring(indexFirst + 1, indexLast), x, completForm));
        }
        
        String[] array = form.split("[+]");
        
        for (String s : array)
        {
            
            if (s.contains("^"))
                form = form.replace(s, String.valueOf(Math.pow(Double.valueOf(s.substring(0, s.indexOf("^"))), Double.valueOf(s.substring(s.indexOf("^") + 1)))));
            else if (s.contains("*"))
                form = form.replace(s, String.valueOf(Double.valueOf(s.substring(0, s.indexOf("*"))) * Double.valueOf(s.substring(s.indexOf("*") + 1))));
            else if (s.contains("/"))
                form = form.replace(s, String.valueOf(Double.valueOf(s.substring(0, s.indexOf("/"))) / Double.valueOf(s.substring(s.indexOf("/") + 1))));
            else if (s.contains("log"))
                form = form.replace(s, String.valueOf(Math.log(Double.valueOf(s.replace("log", "")))));
            else if (s.contains("sqrt"))
                form = form.replace(s, String.valueOf(Math.sqrt(Double.valueOf(s.replace("sqrt", "")))));
            else if (s.contains("xp"))
                form = form.replace(s, String.valueOf(getMathResult(completForm, Double.valueOf(s.replace("xp", "")), completForm)));
        }
        
        double result = 0;
        for (String s : form.split("[+]"))
        {
            try
            {
                result += Double.valueOf(s);
            }
            catch (NumberFormatException e)
            {
                result += 0;
            }
        }
        
        return String.valueOf(result);
    }
    
    public int getExpAtLevel(int i)
    {
        return preCalc != null ? preCalc[i] : calcExpAtLevel(i);
    }
    
    public void addLevelPlayer(String name, int exp, int usedpoints)
    {
        playerMap.put(name, new LevelPlayer(exp, usedpoints));
    }
    
    public Map<String, LevelPlayer> getPlayers()
    {
        return playerMap;
    }
    
    public LevelPlayer getPlayer(Player p)
    {
        return getPlayer(p.getName());
    }
    
    public LevelPlayer getPlayer(String name)
    {
        return playerMap.get(name);
    }
    
    public String getLevelName()
    {
        return levelName;
    }
    
    public void setLevelName(String levelName)
    {
        this.levelName = levelName;
    }
    
    public String getPointName()
    {
        return pointName;
    }
    
    public void setPointName(String pointName)
    {
        this.pointName = pointName;
    }
    
    public String getExpName()
    {
        return expName;
    }
    
    public void setExpName(String expName)
    {
        this.expName = expName;
    }
    
    public String getPointKey()
    {
        return pointKey;
    }
    
    public void setPointKey(String pointKey)
    {
        this.pointKey = pointKey;
    }
    
    public String getExpKey()
    {
        return expKey;
    }
    
    public void setExpKey(String expKey)
    {
        this.expKey = expKey;
    }
    
    public String getLevelKey()
    {
        return levelKey;
    }
    
    public void setLevelKey(String levelKey)
    {
        this.levelKey = levelKey;
    }
    
    public void handleAction(LevelAction action, String name, String player)
    {
        if (!xpperaction.containsKey(name))
            return;
        
        if (!playerMap.containsKey(player))
            playerMap.put(player, new LevelPlayer(0, 0));
        
        playerMap.get(player).addExp(xpperaction.get(action).get(name));
    }
    
    public int getPointsPerLevel()
    {
        return pointsperlevel;
    }
    
    public void setPointsPerLevel(int pointsperlevel)
    {
        this.pointsperlevel = pointsperlevel;
    }
    
    public String getFormula()
    {
        return formula;
    }
    
    public void setFormula(String formula)
    {
        this.formula = formula;
    }
}
