package fr.juststop.dev.kingdomuhc.utils;

import fr.juststop.dev.kingdomuhc.managers.particles.ParticleCircle;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleBuilder;

import java.io.File;

public class Utils {

    public static void deleteFolder(File folder) {
        if(folder.exists()) {
            File[] files = folder.listFiles();
            if(files != null) {
                for (File file : files) {
                    if (file.isDirectory()) deleteFolder(file);
                    else file.delete();
                }
            }
        }

        folder.delete();
    }

    public static void summonParticleCircle(ParticleCircle circle) {
        for (int d = 0; d <= 90; d += 1) {
            Location particleLoc = new Location(circle.getLocation().getWorld(), circle.getLocation().getX(), circle.getLocation().getY(), circle.getLocation().getZ());
            particleLoc.setX(circle.getLocation().getX() + Math.cos(d) * circle.getRadius());
            particleLoc.setZ(circle.getLocation().getZ() + Math.sin(d) * circle.getRadius());
            new ParticleBuilder(circle.getEffect(), particleLoc)
                    .display();
        }
    }

    public static Player isTargetingAPlayer(Player player, int maxDistance) {

        int maxDistanceSquared = maxDistance * maxDistance;
        Vector playerEyeLocation = player.getEyeLocation().toVector();

        for(Entity entity : player.getNearbyEntities(maxDistance, maxDistance, maxDistance)) {
            if(entity instanceof Player) {
                Vector direction = entity.getLocation().toVector().subtract(playerEyeLocation);

                if(playerEyeLocation.dot(direction) > 0) {
                    if(entity.getLocation().distanceSquared(player.getLocation()) <= maxDistanceSquared) {
                        return (Player) entity;
                    }
                }
            }
        }

        return null;

    }

    public static void addItemIfPlayerNotHas(Player player, ItemStack item) {
        for(ItemStack itemStack : player.getInventory().getContents()) {
            if(itemStack != null
                    && itemStack.getItemMeta() != null
                    && itemStack.getItemMeta().getDisplayName() != null
                    && item.getItemMeta().getDisplayName().equals(itemStack.getItemMeta().getDisplayName())
            ) {
                return;
            }
        }

        player.getInventory().addItem(item);
    }

    public static void clearPotionEffects(Player player) {
        for(PotionEffect potionEffect : player.getActivePotionEffects()) {
            player.removePotionEffect(potionEffect.getType());
        }
    }

    public static String formatDuration(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int remaining = seconds % 60;

        if (hours > 0) return String.format("§e%d§6:§e%d§6:§e%d", hours, minutes, remaining);
        else if (minutes > 0) return String.format("§e%d§6:§e%d", minutes, remaining);
        else return String.format("§e%ds", remaining);
    }

}
