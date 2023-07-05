package fr.juststop.dev.kingdomuhc.managers.world;

import com.google.common.base.Strings;
import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.utils.ActionBar;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.enums.MapStatus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.Date;

public class ChunkLoader extends BukkitRunnable {

    private final Date start;

    private double percent;

    private double currentChunkLoad;

    private final double totalChunkToLoad;

    private int cx;

    private int cz;

    private final int radius;

    private boolean finished;

    private final World world;

    public ChunkLoader(int radius) {
        radius += 150;
        this.percent = 0.0D;
        this.totalChunkToLoad = Math.pow(radius, 2.0D) / 64.0D;
        this.currentChunkLoad = 0.0D;
        this.cx = -radius;
        this.cz = -radius;
        this.world = Bukkit.getWorld("game");
        this.world.setGameRuleValue("doFireTick", "false");
        this.world.setGameRuleValue("naturalRegeneration", "false");
        this.radius = radius;
        this.finished = false;
        runTaskTimer(KingdomUHC.getInstance(), 0L, 5L);
        this.start = new Date();
    }

    public void run() {
        for (int i = 0; i < 30 && !this.finished; i++) {
            Location loc = new Location(this.world, this.cx, 0.0D, this.cz);
            loc.getChunk().load(true);
            this.cx += 16;
            this.currentChunkLoad++;
            if (this.cx > this.radius) {
                this.cx = -this.radius;
                this.cz += 16;
                if (this.cz > this.radius) {
                    this.currentChunkLoad = this.totalChunkToLoad;
                    this.finished = true;
                }
            }
        }
        this.percent = this.currentChunkLoad / this.totalChunkToLoad * 100.0D;
        new ActionBar(getProgressBar(this.percent) + " §7"+new DecimalFormat("###.##").format(this.percent) + "%").sendToAll();
        if (this.finished) {
            Bukkit.broadcastMessage(Language.PREFIX.getMessage() + " " + Language.CMD_WORLD_SUB_LOAD_ENDED.getMessage());
            KingdomUHC.getInstance().getGameManager().getConfig().MAP_STATUS = MapStatus.LOADED;
            cancel();
        }
    }

    public static String getProgressBar(double percent) {
        int totalBars = 20;
        int filledBars = (int) ((percent / 100) * totalBars);
        int emptyBars = totalBars - filledBars;

        StringBuilder progressBar = new StringBuilder();
        progressBar.append("§c» ");
        for (int i = 0; i < filledBars; i++) {
            progressBar.append("§a█");
        }
        for (int i = 0; i < emptyBars; i++) {
            progressBar.append("§7█");
        }

        progressBar.append("§c«");
        return progressBar.toString();
    }
}
