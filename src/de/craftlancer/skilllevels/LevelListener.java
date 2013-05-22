package de.craftlancer.skilllevels;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class LevelListener implements Listener
{
    SkillLevels plugin;
    
    public LevelListener(SkillLevels plugin)
    {
        this.plugin = plugin;
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMobKill(EntityDamageByEntityEvent e)
    {
        if (e.getDamager() instanceof Player)
            if (e.getEntity().isDead())
                plugin.handleAction(LevelAction.MOBKILL, e.getEntityType().getName(), ((Player) e.getDamager()).getName());
            else
                plugin.handleAction(LevelAction.MOBDAMAGE, e.getEntityType().getName(), ((Player) e.getDamager()).getName());
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent e)
    {
        plugin.handleAction(LevelAction.BLOCKDESTROY, e.getBlock().getType().name(), e.getPlayer().getName());
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent e)
    {
        plugin.handleAction(LevelAction.BLOCKPLACE, e.getBlock().getType().name(), e.getPlayer().getName());
    }
    
    // TOTEST AnimalTamer#getName() == Player#getName()?
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEntityTame(EntityTameEvent e)
    {
        if (e.getOwner() instanceof Player)
            plugin.handleAction(LevelAction.MOBTAME, e.getEntityType().getName(), e.getOwner().getName());
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onFurnaceExtract(FurnaceExtractEvent e)
    {
        plugin.handleAction(LevelAction.CRAFT, e.getItemType().name(), e.getItemAmount(), e.getPlayer().getName());
    }
    
    // TOTEST HumanEntity#getName() == Player#getName()?
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onItemCraft(CraftItemEvent e)
    {
        plugin.handleAction(LevelAction.CRAFT, e.getRecipe().getResult().getType().name(), e.getRecipe().getResult().getAmount(), e.getWhoClicked().getName());
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onItemConsume(PlayerItemConsumeEvent e)
    {
        plugin.handleAction(LevelAction.ITEMCONSUME, e.getItem().getType().name(), e.getPlayer().getName());
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerFishing(PlayerFishEvent e)
    {
        if (e.getState().equals(State.CAUGHT_FISH))
            plugin.handleAction(LevelAction.EVENT, "FISHING", e.getPlayer().getName());
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onExpChange(PlayerExpChangeEvent e)
    {
        plugin.handleAction(LevelAction.EVENT, "EXPCHANGE", e.getAmount(), e.getPlayer().getName());
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onExpChange(PlayerLevelChangeEvent e)
    {
        plugin.handleAction(LevelAction.EVENT, "EXPCHANGE", e.getNewLevel() - e.getOldLevel(), e.getPlayer().getName());
    }
    
}
