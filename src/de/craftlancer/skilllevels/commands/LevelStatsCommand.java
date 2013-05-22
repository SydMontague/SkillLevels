package de.craftlancer.skilllevels.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.craftlancer.skilllevels.LevelLanguage;
import de.craftlancer.skilllevels.LevelSystem;
import de.craftlancer.skilllevels.SkillLevels;

public class LevelStatsCommand extends LevelSubCommand
{
    public LevelStatsCommand(String permission, SkillLevels plugin)
    {
        super(permission, plugin);
    }
    
    @Override
    protected void execute(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!sender.hasPermission(getPermission()) && sender instanceof Player)
            sender.sendMessage(LevelLanguage.COMMAND_PERMISSION);
        else if (!(sender instanceof Player) && args.length < 2)
            sender.sendMessage(LevelLanguage.COMMAND_ARGUMENTS);
        else if (args.length >= 2 && Bukkit.getPlayerExact(args[1]) == null)
            sender.sendMessage(LevelLanguage.COMMAND_PLAYER_NOT_EXIST);
        else
            for (LevelSystem ls : plugin.getLevelSystems().values())
            {
                if (!ls.hasPlayer(args[1]))
                    continue;
                
                sender.sendMessage(ls.getSystemName() + " - " + ls.getLevelName() + " " + ls.getLevel(args[1]) + " | " + ls.getExp(args[1]) + " " + ls.getExpName() + " | " + ls.getPoints(args[1]) + " " + ls.getPointName());
            }
    }
    
    @Override
    protected List<String> onTabComplete(String[] args)
    {
        return null;
    }
}
