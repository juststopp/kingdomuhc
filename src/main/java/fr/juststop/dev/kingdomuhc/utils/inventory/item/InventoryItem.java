package fr.juststop.dev.kingdomuhc.utils.inventory.item;

import org.bukkit.inventory.ItemStack;

public abstract class InventoryItem {

    private final ItemStack itemStack;
    private final int slot;

    InventoryItem(ItemStack itemStack, int slot) {
        this.itemStack = itemStack;
        this.slot = slot;
    }

    public final ItemStack getItemStack() { return itemStack; }

    public final int getSlot() { return slot; }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof InventoryItem))
            return false;

        InventoryItem inventoryItem = (InventoryItem) obj;
        return inventoryItem.itemStack.equals(itemStack);
    }
}
