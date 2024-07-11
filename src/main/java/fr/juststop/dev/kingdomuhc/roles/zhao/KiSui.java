package fr.juststop.dev.kingdomuhc.roles.zhao;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
import fr.juststop.dev.kingdomuhc.roles.Role;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.enums.Camps;

public class KiSui extends Role {
    public KiSui() {
        super(
                new Language("roles.zhao.kisui.name").getMessage(),
                Camps.ZHAO,
                new Language("roles.zhao.kisui.long_desc").getAsList()
        );
    }

    @Override
    public void init(boolean ignoreEffects) {
        super.init(ignoreEffects);

        UhcPlayer player = KingdomUHC.getInstance().getGameManager().getPlayers().get(this.getPlayer());
        player.setResistancePercentage(20);
        player.setSpeedPercentage(20);
    }

    @Override
    public void playerDied(UhcPlayer player) {
        super.playerDied(player);

        if(player.getRole().getCamp() == Camps.ZHAO) {
            if(player.getResistancePercentage() > 2) player.setResistancePercentage(player.getResistancePercentage() - 2);
            if(player.getSpeedPercentage() > 2) player.setSpeedPercentage(player.getSpeedPercentage() - 2);
        }
    }
}
