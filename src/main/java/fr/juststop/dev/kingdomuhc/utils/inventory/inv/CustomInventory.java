package fr.juststop.dev.kingdomuhc.utils.inventory.inv;

import fr.juststop.dev.kingdomuhc.utils.inventory.Registre;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.InventoryClickHandler;
import fr.juststop.dev.kingdomuhc.utils.inventory.item.InventoryItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class CustomInventory implements ICustomInventory {

    private final List<InventoryClickHandler> clickHandlers;
    private final List<InventoryItem> items;
    private final int slots;
    private final String title;

    public CustomInventory(String title, int slots) {
        this.clickHandlers = new ArrayList<>(3);
        this.items = new ArrayList<>();
        this.title = title;
        this.slots = slots;
    }

    @Override
    public void addHandler(InventoryClickHandler handler) {
        clickHandlers.add(handler);
    }

    @Override
    public void callClickHandlers(InventoryClickHandler.ClickHandler clickHandler) {
        clickHandlers.forEach(handler -> handler.handle(clickHandler));
    }

    @Override
    public void clearClickHandlers() {
        clickHandlers.clear();
    }

    @Override
    public boolean open(Player player) {
        Inventory inv = createInventory();

        player.openInventory(inv);
        Registre.setInventory(this, player.getUniqueId());
        return true;
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public void addItem(InventoryItem item) {
        items.add(item);
    }

    @Override
    public void removeItem(InventoryItem item) {
        items.remove(item);
    }

    @Override
    public InventoryItem getItem(int slot) {
        for(InventoryItem item : items) {
            if(item.getSlot() == slot) return item;
        }

        return null;
    }

    @Override
    public Inventory createInventory() {
        Inventory inv = Bukkit.createInventory(null, slots, title);

        for(InventoryItem inventoryItem : items) {
            inv.setItem(inventoryItem.getSlot(), inventoryItem.getItemStack());
        }

        return inv;
    }
}
