package de.craftlancer.skilllevels.commands;

import java.util.HashMap;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import de.craftlancer.skilllevels.SkillLevels;
import de.craftlancer.skilllevels.Utils;

public class LevelCommands implements TabExecutor
{
    private HashMap<String, LevelSubCommand> commands = new HashMap<String, LevelSubCommand>();
    
    public LevelCommands(SkillLevels plugin)
    {
        commands.put("help", new LevelHelpCommand("levels.command.help", plugin));
        commands.put("stats", new LevelStatsCommand("levels.command.stats", plugin));
        commands.put("add", new LevelAddCommand("levels.command.stats", plugin));
        commands.put("revoke", new LevelRevokeCommand("levels.command.stats", plugin));
        commands.put("set", new LevelSetCommand("levels.command.stats", plugin));
        commands.put("reset", new LevelResetCommand("levels.command.stats", plugin));
        commands.put("reload", new LevelReloadCommand("levels.command.stats", plugin));
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (args.length == 0 || !commands.containsKey(args[0]))
            return false;
        else
            commands.get(args[0]).execute(sender, cmd, label, args);
        
        return true;
    }
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args)
    {
        switch (args.length)
        {
            case 0:
                return null;
            case 1:
                List<String> l = Utils.getMatches(args[0], commands.keySet());
                for (String str : l)
                    if (!sender.hasPermission(commands.get(str).getPermission()))
                        l.remove(l);
                return l;
            default:
                if (!commands.containsKey(args[0]))
                    return null;
                else
                    return commands.get(args[0]).onTabComplete(args);
        }
    }
    
}
