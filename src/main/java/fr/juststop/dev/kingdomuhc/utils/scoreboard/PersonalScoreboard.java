package fr.juststop.dev.kingdomuhc.utils.scoreboard;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
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

        objectiveSign.setLine(0, "§1");
        objectiveSign.setLine(1, "§8︲ §fHost: §e" + (KingdomUHC.getInstance().getGameManager().getConfig().HOST == null ? "§cNon défini" : KingdomUHC.getInstance().getGameManager().getConfig().HOST.getDisplayName()));
        objectiveSign.setLine(2, "§8︲ §fJoueurs: §e" + Bukkit.getOnlinePlayers().stream().filter(player1 -> player1.getGameMode() != GameMode.SPECTATOR).count() + "§6/§e"+KingdomUHC.getInstance().getGameManager().getConfig().SLOTS);
        objectiveSign.setLine(3, "§2");
        objectiveSign.setLine(4, ip);

        objectiveSign.updateLines();
    }

    public void onLogout(){
        objectiveSign.removeReceiver(Bukkit.getServer().getOfflinePlayer(uuid));
    }
}