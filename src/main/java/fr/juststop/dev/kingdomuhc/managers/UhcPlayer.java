package fr.juststop.dev.kingdomuhc.managers;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.roles.Role;
import fr.juststop.dev.kingdomuhc.utils.ActionBar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class UhcPlayer {

    private final Player player;
    private Role role;
    private int fatigue;
    private List<String> actionBar = new ArrayList<>();
    private int actionBarRunnable;

    private double speedPercentage;
    private double strengthPercentage;
    private double resistancePercentage;

    public UhcPlayer(Player player) {
        this.player = player;
        this.role = null;
        this.fatigue = 0;

        this.speedPercentage = 0;
        this.strengthPercentage = 0;
        this.resistancePercentage = 0;
    }

    public Player getPlayer() { return player; }
    public Role getRole() { return role; }
    public int getFatigue() { return fatigue; }

    public double getSpeedPercentage() { return speedPercentage; }
    public double getStrengthPercentage() { return strengthPercentage; }
    public double getResistancePercentage() { return resistancePercentage; }

    public List<String> getActionBar() { return actionBar; }
    public String getActionBarAsString() { return String.join(" §f§l︲§r ", actionBar); }

    public void onJoin() {
        this.actionBarRunnable = new BukkitRunnable() {

            @Override
            public void run() {
                if(getActionBarAsString().length() > 0) new ActionBar(getActionBarAsString()).sendToPlayer(player);
            }

        }.runTaskTimer(KingdomUHC.getInstance(), 0L, 20L).getTaskId();
    }

    public void onQuit() { Bukkit.getScheduler().cancelTask(this.actionBarRunnable); }

    public void setRole(Role role) { this.role = role; }

    public void addFatigue(int fatigue) {
        if(this.fatigue + fatigue >= 100) {
            this.fatigue = 100;
            /* this.role.triggerFatigue100(); */
        } else this.fatigue += fatigue;
    }

    public void removeFatigue(int fatigue) {
        if(this.fatigue - fatigue < 0) this.fatigue = 0;
        else this.fatigue -= fatigue;
    }

    public void clearActionBar() { this.actionBar.clear(); }

    public void setSpeedPercentage(int percentage) { this.speedPercentage = percentage; }
    public void setStrengthPercentage(int percentage) { this.strengthPercentage = percentage; }
    public void setResistancePercentage(int percentage) { this.resistancePercentage = percentage; }
}
