package fr.juststop.dev.kingdomuhc.roles.zhao;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.items.zhao.toujou.Pleasure;
import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
import fr.juststop.dev.kingdomuhc.roles.Role;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.Utils;
import fr.juststop.dev.kingdomuhc.utils.enums.Camps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TouJou extends Role {

    public TouJou() {
        super(
                new Language("roles.zhao.toujou.name").getMessage(),
                Camps.ZHAO,
                new Language("roles.zhao.toujou.long_desc").getAsList()
        );
    }

    @Override
    public void init(boolean ignoreEffects) {
        super.init(ignoreEffects);

        Pleasure pleasure = new Pleasure(this);
        Utils.addItemIfPlayerNotHas(this.getPlayer(), pleasure.getItemStack());
    }

    @Override
    public void runPassif() {
        super.runPassif();

        for(Entity entity : this.getPlayer().getNearbyEntities(10, 10, 10)) {
            if(entity instanceof Player) {
                UhcPlayer player = KingdomUHC.getInstance().getGameManager().getPlayers().get(entity);
                if(player != null && player != this.getPlayer() && player.getRole().getCamp() == Camps.ZHAO) {
                    player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 20, 0, false, false));
                }
            }
        }
    }
}
