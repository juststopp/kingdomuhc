package fr.juststop.dev.kingdomuhc.utils.inventory;

import fr.juststop.dev.kingdomuhc.utils.inventory.inv.CustomInventory;
import fr.juststop.dev.kingdomuhc.utils.inventory.item.CustomInventoryItem;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Registre {

    private static final Map<UUID, CustomInventory> customInventories = new HashMap<>();
    private static final Map<ItemStack, CustomInventoryItem> customInventoryItems = new HashMap<>();

    public static boolean isCustomInventory(UUID player) {
        return customInventories.containsKey(player);
    }
    public static boolean isCustomItem(ItemStack item) { return customInventoryItems.containsKey(item); }

    public static void setInventory(CustomInventory customInventory, UUID player) { customInventories.put(player, customInventory); }
    public static void setItem(ItemStack itemStack, CustomInventoryItem customInventoryItem) { customInventoryItems.put(itemStack, customInventoryItem); }

    public static void removeInventory(UUID player) {
        customInventories.remove(player);
    }
    public static void removeItem(ItemStack itemStack) { customInventoryItems.remove(itemStack); }

    public static CustomInventory getPlayerInventory(UUID player) {
        return customInventories.get(player);
    }
    public static CustomInventoryItem getCustomItem(ItemStack itemStack) { return customInventoryItems.get(itemStack); }

}
