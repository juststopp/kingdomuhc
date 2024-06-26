package fr.juststop.dev.kingdomuhc.managers.game;

import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
import fr.juststop.dev.kingdomuhc.roles.Role;
import fr.juststop.dev.kingdomuhc.roles.zhao.BaNanJi;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameManager {

    private final GameConfig config;
    private HashMap<Player, UhcPlayer> players;
    private List<Role> roles;
    private List<Player> fatiguePlayers;

    public GameManager() {
        this.config = new GameConfig();
        this.players = new HashMap<>();
        this.roles = new ArrayList<>();
        this.fatiguePlayers = new ArrayList<>();
    }

    public GameConfig getConfig() { return config; }
    public HashMap<Player, UhcPlayer> getPlayers() { return players; }
    public List<Role> getRoles() { return roles; }
    public List<Player> getFatiguePlayers() { return fatiguePlayers;}
}
