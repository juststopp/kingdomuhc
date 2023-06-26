package fr.juststop.dev.kingdomuhc.utils.inventory.item;

import fr.juststop.dev.kingdomuhc.utils.inventory.Registre;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemClickHandler;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemInteractHandler;
import org.bukkit.inventory.ItemStack;

public abstract class CustomInventoryItem extends InventoryItem {

    public CustomInventoryItem(ItemStack itemStack, int slot) {
        super(itemStack, slot);
        Registre.setItem(itemStack, this);
    }

    public abstract void handleClick(CustomItemClickHandler handler);

    public abstract void handleInteract(CustomItemInteractHandler handler);
}
