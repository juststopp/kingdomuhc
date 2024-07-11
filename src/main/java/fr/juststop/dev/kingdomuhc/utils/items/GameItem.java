package fr.juststop.dev.kingdomuhc.utils.items;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
import fr.juststop.dev.kingdomuhc.roles.Role;
import fr.juststop.dev.kingdomuhc.utils.Cooldown;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemClickHandler;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemInteractHandler;
import fr.juststop.dev.kingdomuhc.utils.inventory.item.CustomInventoryItem;
import fr.juststop.dev.kingdomuhc.utils.inventory.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public class GameItem extends CustomInventoryItem {

    private final String name;
    private final String[] description;
    private final Cooldown cooldown;
    private final Role roleNeeded;

    public GameItem(String name, String[] description, Cooldown cooldown, ItemStack itemStack, int slot, Role roleNeeded) {
        super(new ItemBuilder(itemStack)
                .setName(name)
                .addLoreLines(description)
                .getItemStack(),
                slot);

        this.name = name;
        this.description = description;
        this.cooldown = cooldown;
        this.roleNeeded = roleNeeded;
    }

    @Override
    public void handleClick(CustomItemClickHandler handler) {};

    @Override
    public void handleInteract(CustomItemInteractHandler handler) {
        handler.setCancelled(false);

        UhcPlayer uhcPlayer = KingdomUHC.getInstance().getGameManager().getPlayers().get(handler.getEvent().getPlayer());
        if(this.roleNeeded != null && !uhcPlayer.getRole().getName().equalsIgnoreCase(this.roleNeeded.getName())) {
            new MessageBuilder(new Language("error_prefix").getMessage())
                    .addText(new Language("items.not_yours").getMessage())
                    .sendMessage(uhcPlayer.getPlayer());

            handler.setCancelled(true);
        }

        if(!handler.isCancelled() && this.cooldown.isActive()) {
            new MessageBuilder(new Language("error_prefix").getMessage())
                    .addText(new Language("items.cooldown").getMessage().replace("%cooldown%", Long.toString(this.getCooldown().getRemaining())))
                    .sendMessage(uhcPlayer.getPlayer());

            handler.setCancelled(true);
        }

    };

    public String getName() { return name; }
    public String[] getDescription() { return description; }
    public Cooldown getCooldown() { return cooldown; }

    public void handleTimer(UhcPlayer player, int duration, Runnable callback) {
        this.getCooldown().start();

        player.getActionBar().add(this.getName());
        Bukkit.getScheduler().runTaskLater(KingdomUHC.getInstance(), () -> {
            player.getActionBar().remove(this.getName());
            callback.run();
        }, duration * 20L);
    }
}
