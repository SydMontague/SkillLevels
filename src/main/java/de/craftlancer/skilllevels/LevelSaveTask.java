package de.craftlancer.skilllevels;

import org.bukkit.scheduler.BukkitRunnable;

public class LevelSaveTask extends BukkitRunnable
{
    @Override
    public void run()
    {
        SkillLevels.getInstance().save();
    }
}
