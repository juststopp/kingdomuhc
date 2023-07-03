package fr.juststop.dev.kingdomuhc.managers.game;

import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class GameManager {

    private final GameConfig config;
    private HashMap<Player, UhcPlayer> players;

    public GameManager() {
        this.config = new GameConfig();
        this.players = new HashMap<>();
    }

    public GameConfig getConfig() { return config; }
    public HashMap<Player, UhcPlayer> getPlayers() { return players; }
}
