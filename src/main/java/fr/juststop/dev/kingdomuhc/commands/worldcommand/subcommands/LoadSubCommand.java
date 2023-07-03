package fr.juststop.dev.kingdomuhc.commands.worldcommand.subcommands;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.utils.ActionBar;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LoadSubCommand extends Command {

    public LoadSubCommand(String name) { super(name, "&7Charge le monde.", "blastmc.uhc.world.load"); }

    @Override
    public void run(Player player, String[] args) {

        new MessageBuilder(KingdomUHC.getInstance().getPrefix())
                .addText("&7Lancement de la &bpré-génération&f.")
                .sendMessage(player);

        World world = player.getWorld();
        int radius = 1000;
        int chunkX = player.getLocation().getBlockX() >> 4;
        int chunkZ = player.getLocation().getBlockZ() >> 4;

        int diameter = radius * 2;
        int totalChunks = (diameter * diameter) / 256;

        new BukkitRunnable() {
            private int loadedChunks = 0;
            private int currentX = chunkX - radius;

            @Override
            public void run() {
                if (currentX <= chunkX + radius) {
                    int currentZ = chunkZ - radius;
                    while (currentZ <= chunkZ + radius) {
                        if (loadedChunks < totalChunks) {
                            Chunk chunk = world.getChunkAt(currentX, currentZ);

                            if(!chunk.isLoaded()) chunk.load();

                            float progress = (float) (loadedChunks + 1) / totalChunks;
                            int progressPercentage = (int) (progress * 100);
                            String progressBar = Utils.generateProgressBar(progress);

                            new ActionBar(progressBar + " §8- §a"+ progressPercentage + "%").sendToAll();

                            loadedChunks++;
                        }

                        currentZ++;
                    }

                    currentX++;
                } else {
                    this.cancel();
                    new MessageBuilder(KingdomUHC.getInstance().getPrefix())
                            .addText("&7La &bpré-généraion &7de la map est &aterminée&f.")
                            .sendMessage(player);
                }
            }
        }.runTaskTimer(KingdomUHC.getInstance(), 0L, 0L);
    }
}
