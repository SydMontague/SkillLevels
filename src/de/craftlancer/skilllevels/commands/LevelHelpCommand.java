package de.craftlancer.skilllevels.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import de.craftlancer.skilllevels.LevelLanguage;
import de.craftlancer.skilllevels.SkillLevels;
import de.craftlancer.skilllevels.Utils;

public class LevelHelpCommand extends LevelSubCommand
{
    private static String[] cmdList = { "help", "stats", "add", "revoke", "set", "reset", "reload" };
    
    public LevelHelpCommand(String permission, SkillLevels plugin)
    {
        super(permission, plugin);
    }
    
    @Override
    protected void execute(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!sender.hasPermission(getPermission()))
            sender.sendMessage(LevelLanguage.COMMAND_PERMISSION);
        else if (args.length == 1 || args[1].equalsIgnoreCase("help"))
        {
            sender.sendMessage(LevelLanguage.HELP_COMMAND_HELP);
            
            if (sender.hasPermission("levels.admin"))
                sender.sendMessage(LevelLanguage.HELP_COMMAND_HELP_ADMIN);
        }
        else if (args[1].equalsIgnoreCase("stats"))
            sender.sendMessage(LevelLanguage.HELP_COMMAND_STATS);
        else if (args[1].equalsIgnoreCase("add"))
            sender.sendMessage(LevelLanguage.HELP_COMMAND_ADD);
        else if (args[1].equalsIgnoreCase("revoke"))
            sender.sendMessage(LevelLanguage.HELP_COMMAND_REVOKE);
        else if (args[1].equalsIgnoreCase("set"))
            sender.sendMessage(LevelLanguage.HELP_COMMAND_SET);
        else if (args[1].equalsIgnoreCase("reset"))
            sender.sendMessage(LevelLanguage.HELP_COMMAND_RESET);
        else if (args[1].equalsIgnoreCase("reload"))
            sender.sendMessage(LevelLanguage.HELP_COMMAND_RELOAD);
    }
    
    @Override
    protected List<String> onTabComplete(String[] args)
    {
        switch (args.length)
        {
            case 2:
                return Utils.getMatches(args[1], cmdList);
            default:
                return null;
        }
    }
    
}
