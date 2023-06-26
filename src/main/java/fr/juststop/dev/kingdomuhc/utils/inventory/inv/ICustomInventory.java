package fr.juststop.dev.kingdomuhc.utils.inventory.inv;

import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.InventoryClickHandler;
import fr.juststop.dev.kingdomuhc.utils.inventory.item.InventoryItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface ICustomInventory {

    void addHandler(InventoryClickHandler handler);

    void callClickHandlers(InventoryClickHandler.ClickHandler clickHandler);

    void clearClickHandlers();

    boolean open(Player player);

    void clear();

    void addItem(InventoryItem item);

    InventoryItem getItem(int slot);

    void removeItem(InventoryItem item);

    Inventory createInventory();

}
