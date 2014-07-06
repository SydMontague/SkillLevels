package de.craftlancer.skilllevels.commands;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.craftlancer.skilllevels.LevelLanguage;
import de.craftlancer.skilllevels.LevelSystem;
import de.craftlancer.skilllevels.LevelUser;
import de.craftlancer.skilllevels.SkillLevels;

public class LevelStatsCommand extends LevelSubCommand
{
    public LevelStatsCommand(String permission, SkillLevels plugin)
    {
        super(permission, plugin);
    }
    
    @SuppressWarnings("deprecation")
    @Override
    protected void execute(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!sender.hasPermission(getPermission()) && sender instanceof Player)
            sender.sendMessage(LevelLanguage.COMMAND_PERMISSION);
        else if (!(sender instanceof Player) && args.length < 2)
            sender.sendMessage(LevelLanguage.COMMAND_ARGUMENTS);
        else
        {
            UUID uuid = null;
            if (args.length < 2 && sender instanceof Player)
                uuid = ((Player) sender).getUniqueId();
            else
            {
                uuid = Bukkit.getOfflinePlayer(args[1]).getUniqueId();
            }
            //TODO allow /level stats for custom levelables
            
            
            for (LevelSystem ls : plugin.getLevelSystems().values())
            {
                if (!ls.hasUser(uuid))
                    continue;
                
                LevelUser user = ls.getUser(uuid);
                
                // TODO externalize
                // "%sysname% - %levelname% %level% | %exp%/%nextexp% %expname% | %points% %pointsname%
                sender.sendMessage(ChatColor.AQUA + ls.getSystemName() + " - " + ls.getLevelName() + " " + user.getLevel() + " | " + user.getExp() + "/" + ls.getExpAtLevel(user.getLevel() + 1) + " " + ls.getExpName() + " | " + user.getPoints() + " " + ls.getPointName());
            }
        }
    }
    
    @Override
    protected List<String> onTabComplete(String[] args)
    {
        return null;
    }
}
