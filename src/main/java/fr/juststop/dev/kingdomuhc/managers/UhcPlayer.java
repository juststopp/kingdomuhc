package fr.juststop.dev.kingdomuhc.managers;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.managers.areas.Area;
import fr.juststop.dev.kingdomuhc.managers.game.GameConfig;
import fr.juststop.dev.kingdomuhc.managers.particles.ParticlesManager;
import fr.juststop.dev.kingdomuhc.roles.Role;
import fr.juststop.dev.kingdomuhc.utils.ActionBar;
import fr.juststop.dev.kingdomuhc.utils.enums.Colors;
import fr.juststop.dev.kingdomuhc.utils.enums.ParticlePlace;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class UhcPlayer {

    private final Player player;
    private final List<String> actionBar = new ArrayList<>();
    private final List<Area> areas = new ArrayList<>();
    private final HashMap<ParticlePlace, Colors> particles = new HashMap<>();

    private int actionBarRunnable;
    private int passifRunnable;

    private int speedPercentage;
    private int strengthPercentage;
    private int resistancePercentage;

    private Role role;
    private boolean alive;

    public UhcPlayer(Player player) {
        this.player = player;
        this.role = null;

        this.speedPercentage = 0;
        this.strengthPercentage = 0;
        this.resistancePercentage = 0;

        this.alive = true;
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
                if(!getActionBarAsString().isEmpty()) new ActionBar(getActionBarAsString()).sendToPlayer(player);

                for(Map.Entry<ParticlePlace, Colors> entry : particles.entrySet()) {
                    ParticlesManager.spawnParticle(player.getLocation(), entry.getKey(), entry.getValue());
                }
            }

        }.runTaskTimer(KingdomUHC.getInstance(), 0L, 1L).getTaskId();
    }

    public void onQuit() { Bukkit.getScheduler().cancelTask(this.actionBarRunnable); }

    public void died() {
        Bukkit.getScheduler().cancelTask(this.passifRunnable);

        Location loc = this.getPlayer().getLocation();

        this.getPlayer().spigot().respawn();
        this.getPlayer().setGameMode(GameMode.SPECTATOR);
        this.getPlayer().teleport(loc);

        this.alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setRole(Role role) {
        this.role = role;
        role.setPlayer(player);

        this.passifRunnable = new BukkitRunnable(){
            @Override
            public void run() {
                role.runPassif();
            }
        }.runTaskTimer(KingdomUHC.getInstance(), 0, 20L).getTaskId();
    }

    public void clearActionBar() { this.actionBar.clear(); }

    public void setSpeedPercentage(int percentage) {
        this.speedPercentage = percentage;
        this.player.setWalkSpeed(0.2F + (((float) percentage / 100F) * 0.2F));
    }
    public void setStrengthPercentage(int percentage) { this.strengthPercentage = percentage; }
    public void setResistancePercentage(int percentage) { this.resistancePercentage = percentage; }

    public void randomTeleport(World world) {
        GameConfig config = KingdomUHC.getInstance().getGameManager().getConfig();

        Random random = new Random();

        int x = random.nextInt(config.START_BORDER) - config.START_BORDER / 2;
        int z = random.nextInt(config.START_BORDER) - config.START_BORDER / 2;
        int y = world.getHighestBlockYAt(x, z);

        Location loc = new Location(world, x, y, z);
        this.getPlayer().teleport(loc);
    }
}
