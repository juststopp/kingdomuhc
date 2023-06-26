package fr.juststop.dev.kingdomuhc.listeners.inventories;

import azuriamc.azuriamod.utils.inventory.Registre;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class ItemRemoveFromInventoryListeners implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemDrop(PlayerDropItemEvent event) {
        if(!event.isCancelled()) Registre.removeItem(event.getItemDrop().getItemStack());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemBreak(PlayerItemBreakEvent event) {
        Registre.removeItem(event.getBrokenItem());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemConsume(PlayerItemConsumeEvent event) {
        if(!event.isCancelled()) Registre.removeItem(event.getItem());
    }

}
