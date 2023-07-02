package fr.juststop.dev.kingdomuhc.managers.game;

public class GameManager {

    private final GameConfig config;

    public GameManager() {
        this.config = new GameConfig();
    }

    public GameConfig getConfig() { return config; }

}
