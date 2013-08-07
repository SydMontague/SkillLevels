package de.craftlancer.skilllevels.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.craftlancer.skilllevels.LevelLanguage;
import de.craftlancer.skilllevels.LevelSystem;
import de.craftlancer.skilllevels.SkillLevels;

public class LevelResetCommand extends LevelSubCommand
{
    public LevelResetCommand(String permission, SkillLevels plugin)
    {
        super(permission, plugin);
    }
    
    @Override
    protected void execute(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!sender.hasPermission(getPermission()) && sender instanceof Player)
            sender.sendMessage(LevelLanguage.COMMAND_PERMISSION);
        else if (args.length < 2)
            sender.sendMessage(LevelLanguage.COMMAND_ARGUMENTS);
        else
        {
            for (LevelSystem s : plugin.getLevelSystems().values())
            {
                if (!s.hasUser(args[1]))
                    continue;
                
                s.setExp(0, args[1]);
                s.setUsedPoints(0, args[1]);
            }
            
            sender.sendMessage(LevelLanguage.RESET_SUCCESS);
            Bukkit.getPlayerExact(args[1]).sendMessage(LevelLanguage.RESET_NOTIFY);
        }
    }
    
    @Override
    protected List<String> onTabComplete(String[] args)
    {
        return null;
    }
    
}
