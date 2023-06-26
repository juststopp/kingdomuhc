package fr.juststop.dev.kingdomuhc.listeners.inventories;

import fr.juststop.dev.kingdomuhc.utils.inventory.Registre;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemClickHandler;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemInteractHandler;
import fr.juststop.dev.kingdomuhc.utils.inventory.inv.CustomInventory;
import fr.juststop.dev.kingdomuhc.utils.inventory.item.CustomInventoryItem;
import fr.juststop.dev.kingdomuhc.utils.inventory.item.InventoryItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();

        if(inventory == null || !Registre.isCustomInventory(player.getUniqueId())) return;

        CustomInventory customInventory = Registre.getPlayerInventory(player.getUniqueId());
        InventoryItem inventoryItem = customInventory.getItem(event.getSlot());

        if(inventoryItem != null) ((CustomInventoryItem) inventoryItem).handleClick(new CustomItemClickHandler(customInventory, event));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if(!Registre.isCustomInventory(player.getUniqueId())) return;

        Registre.removeInventory(player.getUniqueId());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();

        if(itemStack == null || Registre.isCustomInventory(player.getUniqueId())) return;

        CustomInventoryItem customInventoryItem = Registre.getCustomItem(itemStack);
        if(customInventoryItem != null) customInventoryItem.handleInteract(new CustomItemInteractHandler(event));
    }

}
