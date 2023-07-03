package fr.juststop.dev.kingdomuhc.listeners.inventories;

import fr.juststop.dev.kingdomuhc.utils.inventory.Registre;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class ItemRemoveFromInventoryListeners implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemDrop(PlayerDropItemEvent event) {
        if(!event.isCancelled() && Registre.isCustomItem(event.getItemDrop().getItemStack()) && event.getPlayer().getGameMode() != GameMode.CREATIVE) event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemBreak(PlayerItemBreakEvent event) { Registre.removeItem(event.getBrokenItem()); }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemConsume(PlayerItemConsumeEvent event) {
        if(!event.isCancelled() && Registre.isCustomItem(event.getItem())) event.setCancelled(true);
    }

}
