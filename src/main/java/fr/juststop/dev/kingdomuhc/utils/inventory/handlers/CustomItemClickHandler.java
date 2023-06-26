package fr.juststop.dev.kingdomuhc.utils.inventory.handlers;

import fr.juststop.dev.kingdomuhc.utils.inventory.inv.CustomInventory;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CustomItemClickHandler extends InventoryClickHandler.ClickHandler {

    public CustomItemClickHandler(CustomInventory customInventory, InventoryClickEvent event) {
        super(customInventory, event);
    }

}
