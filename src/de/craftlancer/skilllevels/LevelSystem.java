package de.craftlancer.skilllevels;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LevelSystem
{
    private String expKey;
    private String expName;
    private String formula;
    private String levelKey;
    private String systemKey;
    private String systemName;
    
    private String levelName;
    private int maxlevel;
    private Map<String, LevelPlayer> playerMap = new HashMap<String, LevelPlayer>();
    
    private String pointKey;
    private String pointName;
    private int pointsperlevel;
    
    private int[] preCalc = null;
    private Map<Integer, Integer> preCalcOpen = null;
    private Map<LevelAction, Map<String, Integer>> xpperaction = new HashMap<LevelAction, Map<String, Integer>>();
    
    public LevelSystem(String key, String name, int ppl, int maxlevel, String form, Map<LevelAction, Map<String, Integer>> xpmap, String levelName, String levelKey, String pointName, String pointKey, String expName, String expKey)
    {
        this.maxlevel = maxlevel;
        
        if (maxlevel > 0)
            preCalcLevels();
        else
            preCalcOpen = new HashMap<Integer, Integer>(60);
        
        setPointsPerLevel(ppl);
        setFormula(form);
        xpperaction = xpmap;
        
        setSystemName(name);
        setLevelName(levelName);
        setPointName(pointName);
        setExpName(expName);
        setExpKey(expKey);
        setLevelKey(levelKey);
        setPointKey(pointKey);
    }
    
    public void addExp(int amount, Player p)
    {
        addExp(amount, p.getName());
    }
    
    public void addExp(int amount, String p)
    {
        getPlayer(p).addExp(amount);
    }
    
    public void addLevel(int level, Player p)
    {
        addLevel(level, p.getName());
    }
    
    public void addLevel(int level, String p)
    {
        int init = getLevel(p);
        getPlayer(p).addExp(getExpAtLevel(init + level) - getExpAtLevel(init));
    }
    
    public void addLevelPlayer(String name, int exp, int usedpoints)
    {
        playerMap.put(name, new LevelPlayer(exp, usedpoints));
    }
    
    public void addUsedPoints(int amount, Player p)
    {
        addUsedPoints(amount, p.getName());
    }
    
    public void addUsedPoints(int amount, String p)
    {
        getPlayer(p).addUsedPoints(amount);
    }
    
    public int calcExpAtLevel(int x)
    {
        int value = Integer.valueOf(getMathResult(formula, x, formula));
        
        if(preCalcOpen != null)
            preCalcOpen.put(x, value);
        
        return value;
    }
    
    public int getExp(Player p)
    {
        return getExp(p.getName());
    }
    
    public int getExp(String p)
    {
        return getPlayer(p).getExp();
    }
    
    public int getExpAtLevel(int i)
    {
        if (maxlevel == 0)
            return preCalcOpen.containsKey(i) ? preCalcOpen.get(i) : calcExpAtLevel(i);
        else if (i <= maxlevel)
            return preCalc != null ? preCalc[i] : calcExpAtLevel(i);
        else
            return -1;
    }
    
    public String getExpKey()
    {
        return expKey;
    }
    
    public String getExpName()
    {
        return expName;
    }
    
    public String getFormula()
    {
        return formula;
    }
    
    public int getLevel(int exp)
    {
        for (int i = 0; i <= maxlevel; i++)
            if (getExpAtLevel(i) > exp)
                return i - 1;
        
        return -1;
    }
    
    public int getLevel(Player p)
    {
        return getLevel(p.getName());
    }
    
    public int getLevel(String p)
    {
        return getLevel(getPlayer(p).getExp());
    }
    
    public String getLevelKey()
    {
        return levelKey;
    }
    
    public String getLevelName()
    {
        return levelName;
    }
    
    private String getMathResult(String form, double x, String completForm)
    {
        if (x <= 0 || x > maxlevel)
            return "0";
        
        form = form.replace("x", String.valueOf(x));
        form = form.replaceAll("[-]", "+-");
        
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
            if (s.contains("*"))
                form = form.replace(s, String.valueOf(Double.valueOf(getMathResult(s.substring(0, s.indexOf("*")), x, completForm)) * Double.valueOf(getMathResult(s.substring(s.indexOf("*") + 1), x, completForm))));
            else if (s.contains("/"))
                form = form.replace(s, String.valueOf(Double.valueOf(s.substring(0, s.indexOf("/"))) / Double.valueOf(getMathResult(s.substring(s.indexOf("/") + 1), x, completForm))));
            else if (s.contains("log"))
                form = form.replace(s, String.valueOf(Math.log(Double.valueOf(getMathResult(s.replace("log", ""), x, completForm)))));
            else if (s.contains("sqrt"))
                form = form.replace(s, String.valueOf(Math.sqrt(Double.valueOf(getMathResult(s.replace("sqrt", ""), x, completForm)))));
            else if (s.contains("recur"))
                form = form.replace(s, String.valueOf(getExpAtLevel(Double.valueOf(s.replace("recur", "")).intValue())));
            else if (s.contains("^"))
                form = form.replace(s, String.valueOf(Math.pow(Double.valueOf(getMathResult(s.substring(0, s.indexOf("^")), x, completForm)), Double.valueOf(getMathResult(s.substring(s.indexOf("^") + 1), x, completForm)))));
        
        double result = 0;
        for (String s : form.split("[+]"))
            try
            {
                result += Double.valueOf(s);
            }
            catch (NumberFormatException e)
            {
                result += 0;
            }
        
        return String.valueOf(result);
    }
    
    public LevelPlayer getPlayer(Player p)
    {
        return getPlayer(p.getName());
    }
    
    public LevelPlayer getPlayer(String name)
    {
        return playerMap.get(name);
    }
    
    public Map<String, LevelPlayer> getPlayers()
    {
        return playerMap;
    }
    
    public String getPointKey()
    {
        return pointKey;
    }
    
    public String getPointName()
    {
        return pointName;
    }
    
    public int getPoints(Player p)
    {
        return getPoints(p.getName());
    }
    
    public int getPoints(String p)
    {
        return getLevel(p) * getPointsPerLevel() - getPlayer(p).getUsedPoints();
    }
    
    public int getPointsPerLevel()
    {
        return pointsperlevel;
    }
    
    public String getSystemName()
    {
        return systemName;
    }
    
    public int getUsedPoints(Player p)
    {
        return getUsedPoints(p.getName());
    }
    
    public int getUsedPoints(String p)
    {
        return getPlayer(p).getUsedPoints();
    }
    
    public void handleAction(LevelAction action, String name, int amount, String player)
    {
        if (!xpperaction.containsKey(action) || !xpperaction.get(action).containsKey(name))
            return;
        
        if (!playerMap.containsKey(player))
            playerMap.put(player, new LevelPlayer(0, 0));
        
        int initlevel = getLevel(player);
        addExp(xpperaction.get(action).get(name) * amount, player);
        
        if (getLevel(player) > initlevel && Bukkit.getPlayerExact(player) != null)
        {
            String msg = LevelLanguage.LEVEL_UP;
            msg = msg.replace("%level%", String.valueOf(getLevel(player)));
            msg = msg.replace("%system%", getSystemName());
            Bukkit.getPlayerExact(player).sendMessage(msg);
        }
    }
    
    public boolean hasPlayer(Player p)
    {
        return hasPlayer(p.getName());
    }
    
    public boolean hasPlayer(String p)
    {
        return playerMap.containsKey(p);
    }
    
    private void preCalcLevels()
    {
        preCalc = new int[maxlevel + 1];
        
        for (int i = 0; i <= maxlevel; i++)
            preCalc[i] = calcExpAtLevel(i);
    }
    
    public void revokeExp(int amount, Player p)
    {
        revokeExp(amount, p.getName());
    }
    
    public void revokeExp(int amount, String p)
    {
        getPlayer(p).revokeExp(amount);
    }
    
    public void revokeLevel(int level, Player p)
    {
        revokeLevel(level, p.getName());
    }
    
    public void revokeLevel(int level, String p)
    {
        int init = getLevel(p);
        getPlayer(p).revokeExp(getExpAtLevel(init + level) - getExpAtLevel(init));
    }
    
    public void revokeUsedPoints(int amount, Player p)
    {
        revokeUsedPoints(amount, p.getName());
    }
    
    public void revokeUsedPoints(int amount, String p)
    {
        getPlayer(p).revokeUsedPoints(amount);
    }
    
    public void setExp(int amount, Player p)
    {
        setExp(amount, p.getName());
    }
    
    public void setExp(int amount, String p)
    {
        getPlayer(p).setExp(amount);
    }
    
    public void setExpKey(String expKey)
    {
        this.expKey = expKey;
    }
    
    public void setExpName(String expName)
    {
        this.expName = expName;
    }
    
    public void setFormula(String formula)
    {
        this.formula = formula;
    }
    
    public void setLevel(int level, Player p)
    {
        setLevel(level, p.getName());
    }
    
    public void setLevel(int level, String p)
    {
        getPlayer(p).setExp(getExpAtLevel(level));
    }
    
    public void setLevelKey(String levelKey)
    {
        this.levelKey = levelKey;
    }
    
    public void setLevelName(String levelName)
    {
        this.levelName = levelName;
    }
    
    public void setPointKey(String pointKey)
    {
        this.pointKey = pointKey;
    }
    
    public void setPointName(String pointName)
    {
        this.pointName = pointName;
    }
    
    public void setPointsPerLevel(int pointsperlevel)
    {
        this.pointsperlevel = pointsperlevel;
    }
    
    public void setSystemName(String systemName)
    {
        this.systemName = systemName;
    }
    
    public void setUsedPoints(int amount, Player p)
    {
        setUsedPoints(amount, p.getName());
    }
    
    public void setUsedPoints(int amount, String p)
    {
        getPlayer(p).setUsedPoints(amount);
    }
    
    public String getSystemKey()
    {
        return systemKey;
    }
}
