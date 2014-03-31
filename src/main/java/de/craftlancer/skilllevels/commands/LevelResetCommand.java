package de.craftlancer.skilllevels.commands;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.craftlancer.skilllevels.LevelLanguage;
import de.craftlancer.skilllevels.LevelSystem;
import de.craftlancer.skilllevels.LevelUser;
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
        else if (Bukkit.getPlayerExact(args[1]) == null)
            sender.sendMessage(LevelLanguage.COMMAND_PLAYER_NOT_EXIST);
        else
        {
            Player player = Bukkit.getPlayerExact(args[1]);
            UUID uuid = player.getUniqueId();
            
            for (LevelSystem system : plugin.getLevelSystems().values())
            {
                if (!system.hasUser(uuid))
                    continue;
                
                LevelUser user = system.getUser(uuid);
                
                user.setExp(0);
                user.setUsedPoints(0);
            }
            
            sender.sendMessage(LevelLanguage.RESET_SUCCESS);
            player.sendMessage(LevelLanguage.RESET_NOTIFY);
        }
    }
    
    @Override
    protected List<String> onTabComplete(String[] args)
    {
        return null;
    }
    
}
