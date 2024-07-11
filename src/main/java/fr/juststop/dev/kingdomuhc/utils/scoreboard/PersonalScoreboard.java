package fr.juststop.dev.kingdomuhc.utils.scoreboard;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
import fr.juststop.dev.kingdomuhc.managers.game.GameConfig;
import fr.juststop.dev.kingdomuhc.utils.Utils;
import fr.juststop.dev.kingdomuhc.utils.enums.BorderStatus;
import fr.juststop.dev.kingdomuhc.utils.enums.GameStatus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;

import java.util.UUID;

public class PersonalScoreboard {
    private UhcPlayer uhcPlayer;
    private final UUID uuid;
    private final ObjectiveSign objectiveSign;

    PersonalScoreboard(UhcPlayer uhcPlayer){
        this.uhcPlayer = uhcPlayer;
        uuid = uhcPlayer.getPlayer().getUniqueId();
        objectiveSign = new ObjectiveSign("sidebar", "KingdomUHC");

        reloadData();
        objectiveSign.addReceiver(uhcPlayer.getPlayer());
    }

    public void reloadData(){}

    public void setLines(String ip){
        objectiveSign.setDisplayName(ChatColor.translateAlternateColorCodes('&', KingdomUHC.getInstance().getGameManager().getConfig().name));

        GameConfig config = KingdomUHC.getInstance().getGameManager().getConfig();
        objectiveSign.setLine(0, "§1");
        objectiveSign.setLine(1, "§8︲ §fHost: §e" + (KingdomUHC.getInstance().getGameManager().getConfig().HOST == null ? "§cNon défini" : KingdomUHC.getInstance().getGameManager().getConfig().HOST.getDisplayName()));
        objectiveSign.setLine(2, "§8︲ §fJoueurs: §e" + Bukkit.getOnlinePlayers().stream().filter(player1 -> player1.getGameMode() != GameMode.SPECTATOR).count() + "§6/§e" + KingdomUHC.getInstance().getGameManager().getConfig().SLOTS);

        if(config.GAME_STATUS == GameStatus.WAITING || config.GAME_STATUS == GameStatus.STARTING) {
            objectiveSign.setLine(3, "§2");
            objectiveSign.setLine(4, ip);
        } else if(config.GAME_STATUS == GameStatus.INGAME) {
            objectiveSign.setLine(4, "§2");
            objectiveSign.setLine(5, "§8︲ §fTimer: " + Utils.formatDuration(config.GAME_TIMER));

            if(config.PVP_TIMER > config.GAME_TIMER) objectiveSign.setLine(6, "§8︲ §fPvP: " + Utils.formatDuration(config.PVP_TIMER - config.GAME_TIMER));
            else objectiveSign.setLine(6, "§8︲ §fPvP: §aActivé");

            objectiveSign.setLine(7, "§3");
            objectiveSign.setLine(8, "§8︲ §fBordure:");
            if(config.BORDER_STATUS == BorderStatus.FIXED) objectiveSign.setLine(9, "  §8︲ §fStatus: §a" + Utils.formatDuration(config.BORDER_TIMER - config.GAME_TIMER));
            else objectiveSign.setLine(9, "  §8︲ §fStatus: §cEn mouvement");
            objectiveSign.setLine(10, "  §8︲ §fDistance: §e±" + Bukkit.getWorld("game").getWorldBorder().getSize() / 2);

            objectiveSign.setLine(11, "§4");
            objectiveSign.setLine(12, ip);
        }

        objectiveSign.updateLines();
    }

    public void onLogout(){
        objectiveSign.removeReceiver(Bukkit.getServer().getOfflinePlayer(uuid));
    }
}