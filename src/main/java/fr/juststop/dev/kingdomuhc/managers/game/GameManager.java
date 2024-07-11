package fr.juststop.dev.kingdomuhc.managers.game;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.managers.RolesManager;
import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
import fr.juststop.dev.kingdomuhc.roles.Role;
import fr.juststop.dev.kingdomuhc.utils.ActionBar;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.Utils;
import fr.juststop.dev.kingdomuhc.utils.enums.GameStatus;
import fr.juststop.dev.kingdomuhc.utils.enums.PvPStatus;
import fr.juststop.dev.kingdomuhc.utils.enums.RolesStatus;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameManager {

    private final GameConfig config;

    private HashMap<Player, UhcPlayer> players;
    private List<Role> roles;

    private int startTask = 0;

    public GameManager() {
        this.config = new GameConfig();
        this.players = new HashMap<>();
        this.roles = new ArrayList<>();
    }

    public GameConfig getConfig() { return config; }
    public HashMap<Player, UhcPlayer> getPlayers() { return players; }
    public List<Role> getRoles() { return roles; }

    public void start() {
        World world = Bukkit.getServer().getWorld("game");
        world.getWorldBorder().setCenter(0, 0);
        world.getWorldBorder().setSize(config.START_BORDER);

        config.GAME_STATUS = GameStatus.STARTING;

        for(UhcPlayer player : players.values()) {
            player.getPlayer().getInventory().clear();
            Utils.clearPotionEffects(player.getPlayer());
            player.randomTeleport(world);

            new ActionBar(new Language("game.starting.action_bar").getMessage().replace("%player%", player.getPlayer().getName()))
                    .sendToAll();
        }

        config.GAME_STATUS = GameStatus.INGAME;
        startTimer();
    }

    public void cancelStart() {
        if(this.startTask != 0) {
            Bukkit.getScheduler().cancelTask(this.startTask);
            this.startTask = 0;
        }
    }

    public void startTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {
                config.GAME_TIMER += 1;

                if(config.ROLES_STATUS == RolesStatus.WAITING && config.GAME_TIMER.equals(config.ROLES_TIMER)) RolesManager.distributeRoles();
                if(config.PVP_STATUS == PvPStatus.DISABLED && config.GAME_TIMER.equals(config.PVP_TIMER)) {
                    config.PVP_STATUS = PvPStatus.ENABLED;
                    Bukkit.broadcastMessage(new Language("prefix").getMessage() + new Language("timers.pvp.activated"));
                }

                // TIMER MESSAGES
                if(config.ROLES_STATUS == RolesStatus.WAITING) {
                    if(config.ROLES_TIMER - config.GAME_TIMER == 60) Bukkit.broadcastMessage(new Language("prefix").getMessage() + new Language("timers.roles.left").getMessage().replace("%left%", "1 minute"));
                    if(config.ROLES_TIMER - config.GAME_TIMER == 30) Bukkit.broadcastMessage(new Language("prefix").getMessage() + new Language("timers.roles.left").getMessage().replace("%left%", "30 secondes"));
                    if(config.ROLES_TIMER - config.GAME_TIMER <= 10) Bukkit.broadcastMessage(new Language("prefix").getMessage() + new Language("timers.roles.left").getMessage().replace("%left%", config.ROLES_TIMER - config.GAME_TIMER + " secondes"));
                }
            }
        }.runTaskTimer(KingdomUHC.getInstance(), 0L, 20L);
    }
}
