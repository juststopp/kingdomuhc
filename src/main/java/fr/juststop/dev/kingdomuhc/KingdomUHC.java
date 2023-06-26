package fr.juststop.dev.kingdomuhc;

import fr.juststop.dev.kingdomuhc.commands.KingdomCommand;
import fr.juststop.dev.kingdomuhc.listeners.inventories.InventoryClick;
import fr.juststop.dev.kingdomuhc.listeners.inventories.ItemRemoveFromInventoryListeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class KingdomUHC extends JavaPlugin {

    static KingdomUHC instance;
    static Logger logger;
    static final String prefix = "&6Kingdom &8︲ &r";

    private PluginManager pm;

    @Override
    public void onEnable() {

        instance = this;
        logger = Bukkit.getLogger();

        pm = getServer().getPluginManager();

        getLog().log(Level.INFO, "Registering listeners and commands");
        registerListeners();
        registerCommands();

    }

    @Override
    public void onDisable() {  }

    public static KingdomUHC getInstance() { return instance; }
    public static Logger getLog() { return logger; }
    public static String getPrefix() { return prefix; }

    private void registerListeners() {
        pm.registerEvents(new InventoryClick(), this);
        pm.registerEvents(new ItemRemoveFromInventoryListeners(), this);
    }

    private void registerCommands() {
        getCommand("kingdom").setExecutor(new KingdomCommand());
    }
}
