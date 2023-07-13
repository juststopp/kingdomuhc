package fr.juststop.dev.kingdomuhc.utils.items;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemClickHandler;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemInteractHandler;
import fr.juststop.dev.kingdomuhc.utils.inventory.item.CustomInventoryItem;
import fr.juststop.dev.kingdomuhc.utils.inventory.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RolesBook extends CustomInventoryItem {

    public RolesBook() {
        super(new ItemBuilder(new ItemStack(Material.BOOK))
                .setName(Language.ITEM_ROLES_BOOK_NAME.getMessage())
                        .addLoreLines(Language.ITEM_ROLES_BOOK_DESC.getAsLore())
                        .getItemStack()
                , 2);
    }

    @Override
    public void handleClick(CustomItemClickHandler handler) {  }

    @Override
    public void handleInteract(CustomItemInteractHandler handler) {
        new MessageBuilder(Language.ERROR_PREFIX.getMessage())
                .addText(Language.CMD_NOT_IMPLEMENTED.getMessage())
                .sendMessage((Player) handler.getEvent().getPlayer());
    }
}
