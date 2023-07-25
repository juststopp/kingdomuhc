package fr.juststop.dev.kingdomuhc.utils;

import fr.juststop.dev.kingdomuhc.managers.particles.ParticleCircle;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

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

}
