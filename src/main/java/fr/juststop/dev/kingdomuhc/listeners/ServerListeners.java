package fr.juststop.dev.kingdomuhc.listeners;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.items.zhao.bananji.Force;
import fr.juststop.dev.kingdomuhc.items.zhao.toujou.Pleasure;
import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
import fr.juststop.dev.kingdomuhc.roles.zhao.BaNanJi;
import fr.juststop.dev.kingdomuhc.roles.zhao.TouJou;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.items.waiting.RolesBook;
import fr.juststop.dev.kingdomuhc.utils.Language;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ServerListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        e.setJoinMessage(new Language("server.events.player.join").getMessage().replace("%player%", player.getName()));

        if(KingdomUHC.getInstance().getGameManager().getConfig().HOST == null && player.isOp()) {
            KingdomUHC.getInstance().getGameManager().getConfig().HOST = player;

            RolesBook book = new RolesBook();
            player.getInventory().setItem(book.getSlot(), book.getItemStack());

            new MessageBuilder(KingdomUHC.getInstance().getPrefix())
                    .addText(new Language("game.host.you_are_new").getMessage())
                    .sendMessage(player);
        }

        UhcPlayer uhcPlayer = new UhcPlayer(player);
        uhcPlayer.onJoin();

        TouJou role = new TouJou();
        uhcPlayer.setRole(role);
        KingdomUHC.getInstance().getGameManager().getRoles().add(role);

        Pleasure pleasure = new Pleasure(role);
        uhcPlayer.getPlayer().getInventory().addItem(pleasure.getItemStack());

        KingdomUHC.getInstance().getGameManager().getPlayers().put(player, uhcPlayer);
        KingdomUHC.getInstance().getScoreboardManager().onLogin(uhcPlayer.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        UhcPlayer uhcPlayer = KingdomUHC.getInstance().getGameManager().getPlayers().remove(player);

        KingdomUHC.getInstance().getScoreboardManager().onLogout(uhcPlayer.getPlayer());
        uhcPlayer.onQuit();

        e.setQuitMessage(new Language("server.events.player.quit").getMessage().replace("%player%", player.getName()));
    }

}
