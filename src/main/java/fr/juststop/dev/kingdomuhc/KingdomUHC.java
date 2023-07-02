package fr.juststop.dev.kingdomuhc;

import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.commands.kingdomcommand.KingdomCommand;
import fr.juststop.dev.kingdomuhc.commands.kingdomcommand.subcommands.ConfigSubCommand;
import fr.juststop.dev.kingdomuhc.commands.kingdomcommand.subcommands.HelpSubCommand;
import fr.juststop.dev.kingdomuhc.listeners.ServerListeners;
import fr.juststop.dev.kingdomuhc.listeners.inventories.InventoryClick;
import fr.juststop.dev.kingdomuhc.listeners.inventories.ItemRemoveFromInventoryListeners;
import fr.juststop.dev.kingdomuhc.managers.game.GameManager;
import fr.juststop.dev.kingdomuhc.utils.scoreboard.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class KingdomUHC extends JavaPlugin {

    static KingdomUHC instance;

    private Logger logger;
    private final String prefix = "&6Kingdom &8︲ &r";

    private HashMap<String, Command> commands;

    private PluginManager pm;

    private ScoreboardManager scoreboardManager;
    private GameManager gameManager;

    private ScheduledExecutorService executorMonoThread;
    private ScheduledExecutorService scheduledExecutorService;

    @Override
    public void onEnable() {

        instance = this;
        logger = Bukkit.getLogger();

        executorMonoThread = Executors.newScheduledThreadPool(1);
        scheduledExecutorService = Executors.newScheduledThreadPool(16);

        pm = getServer().getPluginManager();
        scoreboardManager = new ScoreboardManager();
        gameManager = new GameManager();

        commands = new HashMap<>();

        getLog().log(Level.INFO, "Registering listeners and commands");
        registerListeners();
        registerCommands();

    }

    @Override
    public void onDisable() { getScoreboardManager().onDisable(); }

    public static KingdomUHC getInstance() { return instance; }
    public Logger getLog() { return logger; }
    public String getPrefix() { return prefix; }
    public HashMap<String, Command> getCommands() { return commands; }

    public ScheduledExecutorService getExecutorMonoThread() { return executorMonoThread; }
    public ScheduledExecutorService getScheduledExecutorService() { return scheduledExecutorService; }

    public ScoreboardManager getScoreboardManager() { return scoreboardManager; }
    public GameManager getGameManager() { return gameManager; }

    private void registerListeners() {
        pm.registerEvents(new InventoryClick(), this);
        pm.registerEvents(new ItemRemoveFromInventoryListeners(), this);

        pm.registerEvents(new ServerListeners(), this);
    }

    private void registerCommands() {
        new KingdomCommand("kingdom")
                .addSubcommand("config", new ConfigSubCommand("config"))
                .addSubcommand("help", new HelpSubCommand("help"))
                .register();
    }
}
