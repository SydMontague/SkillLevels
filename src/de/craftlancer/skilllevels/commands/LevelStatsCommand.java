package de.craftlancer.skilllevels.commands;

import java.util.List;

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
        String lsUser = sender.getName();
        
        if (!sender.hasPermission(getPermission()) && sender instanceof Player)
            sender.sendMessage(LevelLanguage.COMMAND_PERMISSION);
        else if (!(sender instanceof Player) && args.length < 2)
            sender.sendMessage(LevelLanguage.COMMAND_ARGUMENTS);
        else
        {
            if (args.length >= 2)
                lsUser = args[1];
            
            for (LevelSystem ls : plugin.getLevelSystems().values())
            {
                if (!ls.hasUser(lsUser))
                    continue;
                
                sender.sendMessage(ls.getSystemName() + " - " + ls.getLevelName() + " " + ls.getLevel(lsUser) + " | " + ls.getExp(lsUser) + " " + ls.getExpName() + " | " + ls.getPoints(lsUser) + " " + ls.getPointName());
            }
        }
    }
    
    @Override
    protected List<String> onTabComplete(String[] args)
    {
        return null;
    }
}
