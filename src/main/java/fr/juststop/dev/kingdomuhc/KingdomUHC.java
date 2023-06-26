package fr.juststop.dev.kingdomuhc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class KingdomUHC extends JavaPlugin {

    static KingdomUHC instance;
    static Logger logger;

    private PluginManager pm;

    @Override
    public void onEnable() {

        instance = this;
        logger = Bukkit.getLogger();

        pm = getServer().getPluginManager();

    }

    @Override
    public void onDisable() {  }

    public static KingdomUHC getInstance() { return instance; }
    public static Logger gteLog() { return logger; }
}
