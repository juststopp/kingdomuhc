package fr.juststop.dev.kingdomuhc.listeners;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.items.RolesBook;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ServerListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', KingdomUHC.getInstance().getPrefix() + "&a" + player.getName() + " §7vient de rejoindre la partie."));

        if(KingdomUHC.getInstance().getGameManager().getConfig().HOST == null && player.isOp()) {
            KingdomUHC.getInstance().getGameManager().getConfig().HOST = player;

            RolesBook book = new RolesBook();
            player.getInventory().setItem(book.getSlot(), book.getItemStack());

            new MessageBuilder(KingdomUHC.getInstance().getPrefix())
                    .addText("§7Vous êtes désormais l'§6Host §7de la partie.")
                    .sendMessage(player);
        }

        KingdomUHC.getInstance().getScoreboardManager().onLogin(player);

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        KingdomUHC.getInstance().getScoreboardManager().onLogout(player);

        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', KingdomUHC.getInstance().getPrefix() + "&c" + player.getName() + " §7vient de quitter la partie."));
    }

}
