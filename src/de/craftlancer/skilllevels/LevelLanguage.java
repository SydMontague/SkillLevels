package de.craftlancer.skilllevels;

import org.bukkit.configuration.file.FileConfiguration;

public class LevelLanguage
{
    public static String COMMAND_PERMISSION;
    public static String COMMAND_ARGUMENTS;
    public static String COMMAND_PLAYER_NOT_EXIST;
    public static String COMMAND_SYSTEM_NOT_EXIST;
    public static String COMMAND_ADDSETRESET_ARGERROR;
    public static String COMMAND_NOT_A_NUMBER;
    
    public static String HELP_COMMAND_HELP;
    public static String HELP_COMMAND_HELP_ADMIN;
    public static String HELP_COMMAND_STATS;
    public static String HELP_COMMAND_ADD;
    public static String HELP_COMMAND_REVOKE;
    public static String HELP_COMMAND_SET;
    public static String HELP_COMMAND_RESET;
    public static String HELP_COMMAND_RELOAD;
    
    // public static String STATS_COMMAND;
    public static String RESET_SUCCESS;
    public static String RESET_NOTIFY;
    public static String RELOAD_SUCCESS;
    public static String LEVEL_UP;
    
    protected static void loadStrings(FileConfiguration config)
    {
        COMMAND_PERMISSION = config.getString("string.COMMAND_PERMISSION");
        COMMAND_ARGUMENTS = config.getString("string.COMMAND_ARGUMENTS");
        COMMAND_PLAYER_NOT_EXIST = config.getString("string.COMMAND_PLAYER_NOT_EXIST");
        COMMAND_SYSTEM_NOT_EXIST = config.getString("string.COMMAND_SYSTEM_NOT_EXIST");
        COMMAND_ADDSETRESET_ARGERROR = config.getString("string.COMMAND_ADDSETRESET_ARGERROR");
        COMMAND_NOT_A_NUMBER = config.getString("string.COMMAND_NOT_A_NUMBER");
        
        HELP_COMMAND_HELP = config.getString("string.HELP_COMMAND_HELP");
        HELP_COMMAND_HELP_ADMIN = config.getString("string.HELP_COMMAND_HELP_ADMIN");
        HELP_COMMAND_STATS = config.getString("string.HELP_COMMAND_STATS");
        HELP_COMMAND_ADD = config.getString("string.HELP_COMMAND_ADD");
        HELP_COMMAND_REVOKE = config.getString("string.HELP_COMMAND_REVOKE");
        HELP_COMMAND_SET = config.getString("string.HELP_COMMAND_SET");
        HELP_COMMAND_RESET = config.getString("string.HELP_COMMAND_RESET");
        HELP_COMMAND_RELOAD = config.getString("string.HELP_COMMAND_RELOAD");
        
        // STATS_COMMAND = config.getString("string.STATS_COMMAND");
        RESET_SUCCESS = config.getString("string.RESET_SUCCESS");
        RESET_NOTIFY = config.getString("string.RESET_NOTIFY");
        RELOAD_SUCCESS = config.getString("string.RELOAD_SUCCESS");
        LEVEL_UP = config.getString("string.LEVEL_UP");
    }
    
}
