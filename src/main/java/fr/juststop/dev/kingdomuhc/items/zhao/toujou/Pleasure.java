package fr.juststop.dev.kingdomuhc.items.zhao.toujou;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
import fr.juststop.dev.kingdomuhc.roles.Role;
import fr.juststop.dev.kingdomuhc.utils.Cooldown;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemClickHandler;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemInteractHandler;
import fr.juststop.dev.kingdomuhc.utils.items.GameItem;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Pleasure extends GameItem {

    public Pleasure(Role role) {
        super(
                new Language("items.roles.toujou.pleasure.name").getMessage(),
                new Language("items.roles.toujou.pleasure.desc").getAsList(),
                new Cooldown(620),
                new ItemStack(Material.NETHER_STAR),
                0,
                role
        );
    }

    @Override
    public void handleInteract(CustomItemInteractHandler handler) {
        super.handleInteract(handler);
        if(handler.isCancelled()) return;

        handler.getEvent().getPlayer().removePotionEffect(PotionEffectType.REGENERATION);
        handler.getEvent().getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 90 * 20, 1, false, false));

        UhcPlayer player = KingdomUHC.getInstance().getGameManager().getPlayers().get(handler.getEvent().getPlayer());
        for(Entity entity : player.getPlayer().getNearbyEntities(30, 30, 30)) {
            if(entity instanceof Player) {
                ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 90 * 20, 0, false, false));
            }
        }

        this.handleTimer(player, 90, () -> {});
    }
}
