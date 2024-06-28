package fr.juststop.dev.kingdomuhc.roles;

import com.avaje.ebeaninternal.server.type.reflect.KnownImmutable;
import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.enums.Camps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

public class Role {

    private final String name;
    private final Camps camp;
    private final String[] shortDescription;
    private final String longDescription;
    private Player player;

    public Role(String name, Camps camp, String[] shortDescription, String longDescription) {

        this.name = name;
        this.camp = camp;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.player = null;

    }

    public String getName() { return name; }
    public String getColoredName() { return camp.getColor().getChatColor() + name; }
    public Camps getCamp() { return camp; }
    public String[] getShortDescription() { return shortDescription; }
    public String getLongDescription() { return longDescription; }
    public Player getPlayer() { return player; }

    public void setPlayer(Player player) {
        this.player = player;
        this.init(false);
    }

    public void handleDay() {};
    public void handleNight() {};

    public void init(boolean ignoreEffects) {
        for(String msg : Language.splitLore(this.getLongDescription())) {
            new MessageBuilder(new Language("prefix").getMessage())
                    .addText(msg)
                    .sendMessage(this.getPlayer());
        }

        if(!ignoreEffects) {
            for(PotionEffect effect : this.getPlayer().getActivePotionEffects()) {
                this.getPlayer().removePotionEffect(effect.getType());
            }
        }
    };

    public void runPassif() {};
}
