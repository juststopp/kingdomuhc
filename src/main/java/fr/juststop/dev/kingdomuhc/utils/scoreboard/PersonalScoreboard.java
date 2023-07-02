package fr.juststop.dev.kingdomuhc.utils.scoreboard;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PersonalScoreboard {
    private Player player;
    private final UUID uuid;
    private final ObjectiveSign objectiveSign;

    PersonalScoreboard(Player player){
        this.player = player;
        uuid = player.getUniqueId();
        objectiveSign = new ObjectiveSign("sidebar", "KingdomUHC");

        reloadData();
        objectiveSign.addReceiver(player);
    }

    public void reloadData(){}

    public void setLines(String ip){
        objectiveSign.setDisplayName(ChatColor.translateAlternateColorCodes('&', KingdomUHC.getInstance().getGameManager().getConfig().name));

        objectiveSign.setLine(0, "§1");
        objectiveSign.setLine(1, "§8︲ §fHost: §e" + KingdomUHC.getInstance().getGameManager().getConfig().HOST.getDisplayName());
        objectiveSign.setLine(2, "§8︲ §fJoueurs: §e" + Bukkit.getOnlinePlayers().stream().filter(player1 -> player1.getGameMode() != GameMode.SPECTATOR).count() + "§6/§e"+KingdomUHC.getInstance().getGameManager().getConfig().SLOTS);
        objectiveSign.setLine(3, "§2");
        objectiveSign.setLine(4, ip);

        objectiveSign.updateLines();
    }

    public void onLogout(){
        objectiveSign.removeReceiver(Bukkit.getServer().getOfflinePlayer(uuid));
    }
}