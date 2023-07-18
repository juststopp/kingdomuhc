package fr.juststop.dev.kingdomuhc.roles;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.utils.enums.Camps;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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

    public String getName() { return camp.getColor().getChatColor() + name; }
    public Camps getCamp() { return camp; }
    public String[] getShortDescription() { return shortDescription; }
    public String getLongDescription() { return longDescription; }
    public Player getPlayer() { return player; }

    public void setPlayer(Player player) { this.player = player; }

    public void triggerFatigue100() {
        this.player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 300, 0, true, false));
        this.player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 300, 0, true, false));

        KingdomUHC.getInstance().getGameManager().getFatiguePlayers().add(this.player);
    }

    public void handleDay() {};
    public void handleNight() {};
    public void init() {};
}
