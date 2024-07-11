package fr.juststop.dev.kingdomuhc.listeners.players;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
import fr.juststop.dev.kingdomuhc.managers.game.GameConfig;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.enums.GameStatus;
import fr.juststop.dev.kingdomuhc.utils.enums.PvPStatus;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        UhcPlayer uhcPlayer = KingdomUHC.getInstance().getGameManager().getPlayers().get(player);

        GameConfig config = KingdomUHC.getInstance().getGameManager().getConfig();

        if(uhcPlayer != null
                && config.GAME_STATUS == GameStatus.INGAME
                && player.getGameMode() == GameMode.SURVIVAL
                && config.PVP_STATUS == PvPStatus.ENABLED
        ) {
            event.setDeathMessage(null);
            uhcPlayer.died();

            if(config.DEAD_INV != null) {
                for(ItemStack item : config.DEAD_INV) {
                    uhcPlayer.getPlayer().getWorld().dropItemNaturally(uhcPlayer.getPlayer().getLocation(), item);
                }

                for(ItemStack item : player.getInventory().getContents()) {
                    uhcPlayer.getPlayer().getWorld().dropItemNaturally(uhcPlayer.getPlayer().getLocation(), item);
                }
            }

            Bukkit.broadcastMessage("§7§m|----------------------------------|");
            Bukkit.broadcastMessage(" ");
            Bukkit.broadcastMessage("      §e" + uhcPlayer.getPlayer().getName() + " §fest mort.");
            if(uhcPlayer.getRole() != null) Bukkit.broadcastMessage("      Il était " + uhcPlayer.getRole().getCamp().getColor().getChatColor() + uhcPlayer.getRole().getName() + "§f.");
            Bukkit.broadcastMessage(" ");
            Bukkit.broadcastMessage("§7§m|----------------------------------|");

            for(Player p : KingdomUHC.getInstance().getServer().getOnlinePlayers()) {
                UhcPlayer uhcP = KingdomUHC.getInstance().getGameManager().getPlayers().get(p);
                if(uhcP.getRole() != null) uhcP.getRole().playerDied(uhcPlayer);

                p.playSound(p.getLocation(), Sound.WITHER_DEATH, 5, 10);
            }
        } else if(uhcPlayer != null && config.GAME_STATUS == GameStatus.INGAME && config.PVP_STATUS == PvPStatus.DISABLED) {
            event.setDeathMessage(null);

            Bukkit.broadcastMessage("§7§m|----------------------------------|");
            Bukkit.broadcastMessage(" ");
            Bukkit.broadcastMessage("      §e" + uhcPlayer.getPlayer().getName() + " §fest mort.");
            Bukkit.broadcastMessage(" ");
            Bukkit.broadcastMessage("§7§m|----------------------------------|");

            for(Player p : KingdomUHC.getInstance().getServer().getOnlinePlayers()) {
                p.playSound(p.getLocation(), Sound.WITHER_DEATH, 5, 10);
            }

            player.spigot().respawn();
            uhcPlayer.randomTeleport(Bukkit.getWorld("game"));
            player.setGameMode(GameMode.SURVIVAL);
        } else {
            event.setDeathMessage(null);
        }
    }

}
