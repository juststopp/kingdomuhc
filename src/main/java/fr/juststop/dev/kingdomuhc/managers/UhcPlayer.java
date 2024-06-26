package fr.juststop.dev.kingdomuhc.managers;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.managers.areas.Area;
import fr.juststop.dev.kingdomuhc.managers.particles.ParticlesManager;
import fr.juststop.dev.kingdomuhc.roles.Role;
import fr.juststop.dev.kingdomuhc.utils.ActionBar;
import fr.juststop.dev.kingdomuhc.utils.enums.Colors;
import fr.juststop.dev.kingdomuhc.utils.enums.ParticlePlace;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UhcPlayer {

    private final Player player;
    private Role role;
    private List<String> actionBar = new ArrayList<>();
    private List<Area> areas = new ArrayList<>();
    private HashMap<ParticlePlace, Colors> particles = new HashMap<>();
    private int actionBarRunnable;

    private int speedPercentage;
    private int strengthPercentage;
    private int resistancePercentage;

    public UhcPlayer(Player player) {
        this.player = player;
        this.role = null;

        this.speedPercentage = 0;
        this.strengthPercentage = 0;
        this.resistancePercentage = 0;

    }

    public Player getPlayer() { return player; }
    public Role getRole() { return role; }
    public HashMap<ParticlePlace, Colors> getParticles() { return particles; }
    public List<Area> getAreas() { return areas; }

    public int getSpeedPercentage() { return speedPercentage; }
    public int getStrengthPercentage() { return strengthPercentage; }
    public int getResistancePercentage() { return resistancePercentage; }

    public List<String> getActionBar() { return actionBar; }
    public String getActionBarAsString() { return String.join(" §f§l︲§r ", actionBar); }

    public void onJoin() {
        this.actionBarRunnable = new BukkitRunnable() {

            @Override
            public void run() {
                if(getActionBarAsString().length() > 0) new ActionBar(getActionBarAsString()).sendToPlayer(player);

                for(Map.Entry<ParticlePlace, Colors> entry : particles.entrySet()) {
                    ParticlesManager.spawnParticle(player.getLocation(), entry.getKey(), entry.getValue());
                }
            }

        }.runTaskTimer(KingdomUHC.getInstance(), 0L, 1L).getTaskId();
    }

    public void onQuit() { Bukkit.getScheduler().cancelTask(this.actionBarRunnable); }

    public void setRole(Role role) {
        this.role = role;
        role.setPlayer(player);
    }

    public void clearActionBar() { this.actionBar.clear(); }

    public void setSpeedPercentage(int percentage) {
        this.speedPercentage = percentage;
        this.player.setWalkSpeed(0.2F + (((float) percentage / 100F) * 0.2F));
    }
    public void setStrengthPercentage(int percentage) { this.strengthPercentage = percentage; }
    public void setResistancePercentage(int percentage) { this.resistancePercentage = percentage; }
}
