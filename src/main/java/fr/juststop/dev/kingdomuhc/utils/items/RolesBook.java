package fr.juststop.dev.kingdomuhc.utils.items;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemClickHandler;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemInteractHandler;
import fr.juststop.dev.kingdomuhc.utils.inventory.item.CustomInventoryItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RolesBook extends CustomInventoryItem {

    public RolesBook() { super(new ItemStack(Material.BOOK), 2); }

    @Override
    public void handleClick(CustomItemClickHandler handler) {  }

    @Override
    public void handleInteract(CustomItemInteractHandler handler) {
        new MessageBuilder(KingdomUHC.getInstance().getPrefix())
                .addText("§cCette fonctionnalité n'a pas encore été implémentée.")
                .sendMessage((Player) handler.getEvent().getPlayer());
    }
}
