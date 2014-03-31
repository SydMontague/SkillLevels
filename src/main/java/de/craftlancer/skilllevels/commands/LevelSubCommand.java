package de.craftlancer.skilllevels.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import de.craftlancer.skilllevels.SkillLevels;

// TODO update to latest CommandSystem version
public abstract class LevelSubCommand
{
    String permission = "";
    SkillLevels plugin;
    
    public LevelSubCommand(String permission, SkillLevels plugin)
    {
        this.permission = permission;
        this.plugin = plugin;
    }
    
    public String getPermission()
    {
        return permission;
    }
    
    protected abstract void execute(CommandSender sender, Command cmd, String label, String[] args);
    
    protected abstract List<String> onTabComplete(String[] args);
}
