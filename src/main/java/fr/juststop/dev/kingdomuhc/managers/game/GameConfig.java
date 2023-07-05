package fr.juststop.dev.kingdomuhc.managers.game;

import fr.juststop.dev.kingdomuhc.roles.Role;
import fr.juststop.dev.kingdomuhc.scenarios.Scenarios;
import fr.juststop.dev.kingdomuhc.utils.enums.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class GameConfig {

    public String name = "&eKingdom UHC";
    public Player HOST = null;
    public Integer SLOTS = 60;

    public GameStatus GAME_STATUS = GameStatus.WAITING;
    public BorderStatus BORDER_STATS = BorderStatus.FIXED;
    public PvPStatus PVP_STATUS = PvPStatus.DISABLED;
    public RolesStatus ROLES_STATUS = RolesStatus.WAITING;
    public MapStatus MAP_STATUS = MapStatus.NONE;

    public Integer GAME_TIMER = 0;
    public Integer BORDER_TIMER = 60 * 90; // 1h30
    public Integer PVP_TIMER = 60 * 20; // 20m
    public Integer ROLES_TIMER = 60 * 20; // 20m

    public List<Role> ENABLED_ROLES = new ArrayList<>(); // Liste des rôles ajoutés à la compo.
    public List<Scenarios> ENABLED_SCENARIOS = new ArrayList<>(); // Liste des scénarios ajoutés.

    public Inventory START_INV; // Inventaire de départ;
    public Inventory DEAD_INV; // Inventaire de mort;

    public GameConfig() {};

}
