package fr.juststop.dev.kingdomuhc.items.zhao;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.roles.Role;
import fr.juststop.dev.kingdomuhc.utils.Cooldown;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemClickHandler;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemInteractHandler;
import fr.juststop.dev.kingdomuhc.utils.items.GameItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Force extends GameItem {

    public Force(Role role) {
        super(new Language("items.roles.bananji.force.name").getMessage(),
                new Language("items.roles.bananji.force.desc").getAsList(),
                new Cooldown(300),
                new ItemStack(Material.NETHER_STAR),
                0,
                role
        );
    }

    @Override
    public void handleClick(CustomItemClickHandler handler) {  }

    @Override
    public void handleInteract(CustomItemInteractHandler handler) {
        super.handleInteract(handler);
        if(handler.isCancelled()) return;

        handler.getEvent().getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        handler.getEvent().getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 60 * 20, 1, true, false));
        handler.getEvent().getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60 * 20, 0, true, false));

        this.handleTimer(KingdomUHC.getInstance().getGameManager().getPlayers().get(handler.getEvent().getPlayer()), 60, () -> {
            handler.getEvent().getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 99999, 0, true, false));
        });
    }
}
