package fr.juststop.dev.kingdomuhc.listeners;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.items.waiting.host.Config;
import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
import fr.juststop.dev.kingdomuhc.managers.game.GameConfig;
import fr.juststop.dev.kingdomuhc.managers.game.GameManager;
import fr.juststop.dev.kingdomuhc.roles.zhao.BaNanJi;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.items.waiting.RolesBook;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.Utils;
import fr.juststop.dev.kingdomuhc.utils.enums.GameStatus;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ServerListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.getInventory().clear();

        GameManager manager = KingdomUHC.getInstance().getGameManager();
        GameConfig config = manager.getConfig();

        UhcPlayer UHCPlayer = manager.getPlayers().get(player);
        if((config.GAME_STATUS == GameStatus.STARTING || config.GAME_STATUS == GameStatus.INGAME)
                && !config.NON_PLAYER_SPECTATORS_ALLOWED
                && UHCPlayer == null
        ) {
            e.setJoinMessage(null);
            player.kickPlayer(new Language("server.events.player.no_specs").getMessage());

            return;
        }

        e.setJoinMessage(new Language("server.events.player.join").getMessage().replace("%player%", player.getName()));

        if(UHCPlayer == null && config.GAME_STATUS == GameStatus.WAITING) {
            player.setGameMode(GameMode.SURVIVAL);
            Utils.clearPotionEffects(player);

            player.teleport(new Location(Bukkit.getWorld("world"), 0.5, 210, 0.5));

            RolesBook book = new RolesBook();
            player.getInventory().setItem(book.getSlot(), book.getItemStack());

            if(config.HOST == null && player.isOp()) {
                config.HOST = player;

                Config configItem = new Config();
                player.getInventory().setItem(configItem.getSlot(), configItem.getItemStack());

                new MessageBuilder(new Language("prefix").getMessage())
                        .addText(new Language("game.host.you_are_new").getMessage())
                        .sendMessage(player);
            }

            BaNanJi baNanJi = new BaNanJi();

            manager.getRoles().add(baNanJi);

            UhcPlayer uhcPlayer = new UhcPlayer(player);
            uhcPlayer.onJoin();

            KingdomUHC.getInstance().getGameManager().getPlayers().put(player, uhcPlayer);
            KingdomUHC.getInstance().getScoreboardManager().onLogin(uhcPlayer.getPlayer());
        } else if(UHCPlayer != null) {
            KingdomUHC.getInstance().getScoreboardManager().onLogin(UHCPlayer.getPlayer());

            if(!UHCPlayer.isAlive()) player.setGameMode(GameMode.SPECTATOR);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        UhcPlayer uhcPlayer = KingdomUHC.getInstance().getGameManager().getPlayers().get(player);

        if(uhcPlayer != null) {
            KingdomUHC.getInstance().getScoreboardManager().onLogout(uhcPlayer.getPlayer());
            uhcPlayer.onQuit();

            e.setQuitMessage(new Language("server.events.player.quit").getMessage().replace("%player%", player.getName()));
        } else {
            e.setQuitMessage(null);
        }
    }

}
