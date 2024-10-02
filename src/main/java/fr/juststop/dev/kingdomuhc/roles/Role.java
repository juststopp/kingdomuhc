package fr.juststop.dev.kingdomuhc.roles;

import com.avaje.ebeaninternal.server.type.reflect.KnownImmutable;
import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.enums.Camps;
import fr.juststop.dev.kingdomuhc.utils.items.GameItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class Role {

    private final String name;
    private final Camps camp;
    private final String[] longDescription;
    private Player player;
    private ArrayList<GameItem> items;

    public Role(String name, Camps camp, String[] longDescription) {

        this.name = name;
        this.camp = camp;
        this.longDescription = longDescription;
        this.player = null;

    }

    public String getName() { return name; }
    public String getColoredName() { return camp.getColor().getChatColor() + name; }
    public Camps getCamp() { return camp; }
    public String[] getLongDescription() { return longDescription; }
    public Player getPlayer() { return player; }

    public void addItem(GameItem item) { items.add(item); }
    public void removeItem(GameItem item) { items.remove(item); }
    public boolean hasItem(GameItem item) { return items.contains(item); }

    public void setPlayer(Player player) {
        this.player = player;
        this.init(false);
    }

    public void handleDay() {};
    public void handleNight() {};

    public void init(boolean ignoreEffects) {
        for(String msg : this.getLongDescription()) {
            new MessageBuilder(" ")
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
    public void playerDied(UhcPlayer player) {}
}
