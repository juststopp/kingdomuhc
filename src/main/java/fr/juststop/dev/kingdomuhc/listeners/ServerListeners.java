package fr.juststop.dev.kingdomuhc.listeners;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.items.qin.rishin.CommandantDe1000Hommes;
import fr.juststop.dev.kingdomuhc.items.qin.rishin.CommandantDe100Hommes;
import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
import fr.juststop.dev.kingdomuhc.roles.qin.RiShin;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.items.waiting.RolesBook;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ServerListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        e.setJoinMessage(Language.PLAYER_JOIN.getMessage().replace("%player%", player.getName()));

        if(KingdomUHC.getInstance().getGameManager().getConfig().HOST == null && player.isOp()) {
            KingdomUHC.getInstance().getGameManager().getConfig().HOST = player;

            RolesBook book = new RolesBook();
            player.getInventory().setItem(book.getSlot(), book.getItemStack());

            new MessageBuilder(KingdomUHC.getInstance().getPrefix())
                    .addText(Language.GAME_NEW_HOST.getMessage())
                    .sendMessage(player);
        }

        UhcPlayer uhcPlayer = new UhcPlayer(player);
        uhcPlayer.onJoin();

        RiShin role = new RiShin();
        uhcPlayer.setRole(role);
        KingdomUHC.getInstance().getGameManager().getRoles().add(role);

        CommandantDe1000Hommes item = new CommandantDe1000Hommes(role);
        uhcPlayer.getPlayer().getInventory().addItem(item.getItemStack());

        KingdomUHC.getInstance().getGameManager().getPlayers().put(player, uhcPlayer);
        KingdomUHC.getInstance().getScoreboardManager().onLogin(uhcPlayer.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        UhcPlayer uhcPlayer = KingdomUHC.getInstance().getGameManager().getPlayers().remove(player);

        KingdomUHC.getInstance().getScoreboardManager().onLogout(uhcPlayer.getPlayer());
        uhcPlayer.onQuit();

        e.setQuitMessage(Language.PLAYER_QUIT.getMessage().replace("%player%", player.getName()));
    }

}
