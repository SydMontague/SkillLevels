package de.craftlancer.skilllevels.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.craftlancer.skilllevels.LevelLanguage;
import de.craftlancer.skilllevels.SkillLevels;

public class LevelReloadCommand extends LevelSubCommand
{
    public LevelReloadCommand(String permission, SkillLevels plugin)
    {
        super(permission, plugin);
    }
    
    @Override
    protected void execute(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!sender.hasPermission(getPermission()) && sender instanceof Player)
            sender.sendMessage(LevelLanguage.COMMAND_PERMISSION);
        else
        {
            plugin.reload();
            sender.sendMessage(LevelLanguage.RELOAD_SUCCESS);
        }
    }
    
    @Override
    protected List<String> onTabComplete(String[] args)
    {
        return null;
    }
}
