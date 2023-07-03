package fr.juststop.dev.kingdomuhc.managers.game;

import fr.juststop.dev.kingdomuhc.utils.enums.GameStatus;
import org.bukkit.entity.Player;

public class GameConfig {

    public String name = "&eKingdom UHC";
    public Player HOST = null;
    public Integer SLOTS = 60;
    public GameStatus STATUS = GameStatus.WAITING;

    public GameConfig() {};

}
