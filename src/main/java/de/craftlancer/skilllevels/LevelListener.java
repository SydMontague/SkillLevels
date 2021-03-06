package de.craftlancer.skilllevels;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class LevelListener implements Listener
{
    SkillLevels plugin;
    
    public LevelListener(SkillLevels plugin)
    {
        this.plugin = plugin;
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onSpawnerSpawn(CreatureSpawnEvent e)
    {
        if (!plugin.getConfig().getBoolean("general.preventSpawnerLeveling"))
            return;
        
        switch (e.getSpawnReason())
        {
            case SPAWNER:
                e.getEntity().setMetadata("SkillLevels.ignore", new FixedMetadataValue(plugin, null));
                break;
            default:
                return;
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMobDamage(EntityDamageByEntityEvent e)
    {
        if (e.getEntity().hasMetadata("SkillLevels.ignore"))
            return;
        
        if (e.getDamager().getType().equals(EntityType.PLAYER))
            plugin.handleAction(LevelAction.MOBDAMAGE, e.getEntityType().name(), (int) e.getDamage(), ((Player) e.getDamager()));
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMobKill(EntityDeathEvent e)
    {
        if (e.getEntity().hasMetadata("SkillLevels.ignore"))
            return;
        
        if (e.getEntity().getKiller() != null)
            plugin.handleAction(LevelAction.MOBKILL, e.getEntityType().name(), 1, e.getEntity().getKiller());
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent e)
    {
        if (e.getBlock().hasMetadata("SkillLevels.ignore"))
            return;
        
        plugin.handleAction(LevelAction.BLOCKDESTROY, e.getBlock().getType().name(), e.getPlayer());
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent e)
    {
        plugin.handleAction(LevelAction.BLOCKPLACE, e.getBlock().getType().name(), e.getPlayer());
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEntityTame(EntityTameEvent e)
    {
        if (e.getOwner() instanceof Player)
            plugin.handleAction(LevelAction.MOBTAME, e.getEntityType().name(), (Player) e.getOwner());
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onFurnaceExtract(FurnaceExtractEvent e)
    {
        plugin.handleAction(LevelAction.CRAFT, e.getItemType().name(), e.getItemAmount(), e.getPlayer());
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onItemCraft(CraftItemEvent e)
    {
        if (e.getWhoClicked().getType().equals(EntityType.PLAYER))
            plugin.handleAction(LevelAction.CRAFT, e.getRecipe().getResult().getType().name(), e.getRecipe().getResult().getAmount(), (Player) e.getWhoClicked());
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onItemConsume(PlayerItemConsumeEvent e)
    {
        plugin.handleAction(LevelAction.ITEMCONSUME, e.getItem().getType().name(), e.getPlayer());
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerFishing(PlayerFishEvent e)
    {
        if (e.getState().equals(State.CAUGHT_FISH))
            plugin.handleAction(LevelAction.EVENT, "FISHING", e.getPlayer());
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onExpChange(PlayerExpChangeEvent e)
    {
        plugin.handleAction(LevelAction.EVENT, "EXPCHANGE", e.getAmount(), e.getPlayer());
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onExpChange(PlayerLevelChangeEvent e)
    {
        plugin.handleAction(LevelAction.EVENT, "LEVELCHANGE", e.getNewLevel() - e.getOldLevel(), e.getPlayer());
    }
}
