package fr.juststop.dev.kingdomuhc;

import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.commands.kingdomcommand.KingdomCommand;
import fr.juststop.dev.kingdomuhc.commands.kingdomcommand.subcommands.ConfigSubCommand;
import fr.juststop.dev.kingdomuhc.commands.kingdomcommand.subcommands.EffectsSubCommand;
import fr.juststop.dev.kingdomuhc.commands.kingdomcommand.subcommands.HelpSubCommand;
import fr.juststop.dev.kingdomuhc.commands.kingdomcommand.subcommands.RoleSubCommand;
import fr.juststop.dev.kingdomuhc.commands.languagecommand.LanguageCommand;
import fr.juststop.dev.kingdomuhc.commands.languagecommand.subcommands.ReloadSubCommand;
import fr.juststop.dev.kingdomuhc.commands.worldcommand.WorldCommand;
import fr.juststop.dev.kingdomuhc.commands.worldcommand.subcommands.LoadSubCommand;
import fr.juststop.dev.kingdomuhc.commands.worldcommand.subcommands.createsubcommand.CreateSubCommand;
import fr.juststop.dev.kingdomuhc.commands.worldcommand.subcommands.createsubcommand.subcommands.ConfirmSubCommand;
import fr.juststop.dev.kingdomuhc.listeners.ServerListeners;
import fr.juststop.dev.kingdomuhc.listeners.inventories.InventoryClick;
import fr.juststop.dev.kingdomuhc.listeners.inventories.ItemRemoveFromInventoryListeners;
import fr.juststop.dev.kingdomuhc.listeners.players.DamagesManager;
import fr.juststop.dev.kingdomuhc.listeners.players.DeathListener;
import fr.juststop.dev.kingdomuhc.managers.game.GameManager;
import fr.juststop.dev.kingdomuhc.managers.particles.WorldParticlesScheduler;
import fr.juststop.dev.kingdomuhc.managers.world.WorldManager;
import fr.juststop.dev.kingdomuhc.utils.Language;
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

    private HashMap<String, Command> commands;

    private PluginManager pm;

    private ScoreboardManager scoreboardManager;
    private GameManager gameManager;
    private WorldManager worldManager;

    private ScheduledExecutorService executorMonoThread;
    private ScheduledExecutorService scheduledExecutorService;

    @Override
    public void onEnable() {

        instance = this;
        logger = Bukkit.getLogger();

        executorMonoThread = Executors.newScheduledThreadPool(1);
        scheduledExecutorService = Executors.newScheduledThreadPool(16);

        Language.init();

        pm = getServer().getPluginManager();
        scoreboardManager = new ScoreboardManager();
        gameManager = new GameManager();
        worldManager = new WorldManager();

        commands = new HashMap<>();

        getLog().log(Level.INFO, "Registering listeners and commands");
        registerListeners();
        registerCommands();

        worldManager.replaceBiomes();
        new WorldParticlesScheduler().runTaskTimer(this, 0L, 5L);
    }

    @Override
    public void onDisable() {
        getScoreboardManager().onDisable();
    }

    public static KingdomUHC getInstance() { return instance; }
    public Logger getLog() { return logger; }
    public HashMap<String, Command> getCommands() { return commands; }

    public ScheduledExecutorService getExecutorMonoThread() { return executorMonoThread; }
    public ScheduledExecutorService getScheduledExecutorService() { return scheduledExecutorService; }

    public ScoreboardManager getScoreboardManager() { return scoreboardManager; }
    public GameManager getGameManager() { return gameManager; }
    public WorldManager getWorldManager() { return worldManager; }

    private void registerListeners() {
        pm.registerEvents(new InventoryClick(), this);
        pm.registerEvents(new ItemRemoveFromInventoryListeners(), this);

        pm.registerEvents(new ServerListeners(), this);
        pm.registerEvents(new DamagesManager(), this);
        pm.registerEvents(new DeathListener(), this);
    }

    private void registerCommands() {
        new KingdomCommand("kingdom")
                .addSubcommand(new ConfigSubCommand("config"))
                .addSubcommand(new HelpSubCommand("help"))
                .addSubcommand(new EffectsSubCommand("effects"))
                .addSubcommand(new RoleSubCommand("role"))
                .register();

        new WorldCommand("world")
                .addSubcommand(new CreateSubCommand("create")
                        .addSubcommand(new ConfirmSubCommand("confirm"))
                )
                .addSubcommand(new LoadSubCommand("load"))
                .register();

        new LanguageCommand("language")
                .addSubcommand(new ReloadSubCommand("reload"))
                .register();
    }
}
