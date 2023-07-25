package fr.juststop.dev.kingdomuhc.managers.areas;

import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;

public class AreaOptions {

    private UhcPlayer player;

    public int radius;
    public boolean fallDamage;
    public int damageResistanceBackward;

    public AreaOptions(UhcPlayer player, int radius, boolean fallDamage, int damageResistanceBackward) {

        this.player = player;
        this.radius = radius;
        this.fallDamage = fallDamage;
        this.damageResistanceBackward = damageResistanceBackward;

    }

    public UhcPlayer getPlayer() { return player; }

}
