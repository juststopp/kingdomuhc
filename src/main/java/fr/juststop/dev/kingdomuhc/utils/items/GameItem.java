package fr.juststop.dev.kingdomuhc.utils.items;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
import fr.juststop.dev.kingdomuhc.roles.Role;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemClickHandler;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemInteractHandler;
import fr.juststop.dev.kingdomuhc.utils.inventory.item.CustomInventoryItem;
import fr.juststop.dev.kingdomuhc.utils.inventory.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public class GameItem extends CustomInventoryItem {

    private final String name;
    private final String[] description;
    private final int fatigueUsage;
    private Role roleNeeded;

    public GameItem(String name, String[] description, int fatigueUsage, ItemStack itemStack, int slot, Role roleNeeded) {
        super(new ItemBuilder(itemStack)
                .setName(name)
                .addLoreLines(description)
                .getItemStack(),
                slot);

        this.name = name;
        this.description = description;
        this.fatigueUsage = fatigueUsage;
        this.roleNeeded = roleNeeded;
    }

    @Override
    public void handleClick(CustomItemClickHandler handler) {};

    @Override
    public void handleInteract(CustomItemInteractHandler handler) {
        handler.setCancelled(false);

        UhcPlayer uhcPlayer = KingdomUHC.getInstance().getGameManager().getPlayers().get(handler.getEvent().getPlayer());
        if(this.roleNeeded != null && !uhcPlayer.getRole().getName().equalsIgnoreCase(this.roleNeeded.getName())) {
            new MessageBuilder(Language.ERROR_PREFIX.getMessage())
                    .addText(Language.ITEM_NOT_YOURS.getMessage())
                    .sendMessage(uhcPlayer.getPlayer());

            handler.setCancelled(true);
        }

    };

    public String getName() { return name; }
    public String[] getDescription() { return description; }
    public int getFatigueUsage() { return fatigueUsage; }
}
