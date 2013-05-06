package de.craftlancer.skilllevels;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class SkillListener implements Listener
{
    SkillLevels plugin;
    
    public SkillListener(SkillLevels plugin)
    {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onMobKill(EntityDamageByEntityEvent e)
    {
        if(!e.getEntity().isDead() || !(e.getDamager() instanceof Player))
            return;
        
        plugin.getSkillPlayer((Player) e.getDamager()).addExp(plugin.getMobKillExp(e.getEntityType()));
    }
}
