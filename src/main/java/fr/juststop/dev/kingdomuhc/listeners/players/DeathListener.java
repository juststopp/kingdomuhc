package fr.juststop.dev.kingdomuhc.listeners.players;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.enums.PvPStatus;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        UhcPlayer uhcPlayer = KingdomUHC.getInstance().getGameManager().getPlayers().get(player);

        if(uhcPlayer != null
                && player.getGameMode() == GameMode.SURVIVAL
                && KingdomUHC.getInstance().getGameManager().getConfig().PVP_STATUS == PvPStatus.ENABLED
        ) {
            event.setDeathMessage(null);

            Bukkit.broadcastMessage("§7§m|-----------------------------------------|");
            Bukkit.broadcastMessage(new Language("prefix").getMessage() + "§6§l" + uhcPlayer.getPlayer().getName() + "&fest mort. Il était " + uhcPlayer.getRole().getCamp().getColor().getChatColor() + uhcPlayer.getRole().getName() + "&f.");
            Bukkit.broadcastMessage("§7§m|-----------------------------------------|");

            for(Player p : KingdomUHC.getInstance().getServer().getOnlinePlayers()) {
                UhcPlayer uhcP = KingdomUHC.getInstance().getGameManager().getPlayers().get(p);
                uhcP.getRole().playerDied(uhcPlayer);

                p.playSound(p.getLocation(), Sound.WITHER_DEATH, 5, 10);
            }
        }
    }

}
