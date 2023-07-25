package fr.juststop.dev.kingdomuhc.managers.particles;

import fr.juststop.dev.kingdomuhc.utils.enums.Colors;
import fr.juststop.dev.kingdomuhc.utils.enums.ParticlePlace;
import org.bukkit.Location;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.ArrayList;
import java.util.List;

public class ParticlesManager {

    private static List<ParticleCircle> circles = new ArrayList<>();

    public static List<ParticleCircle> getCircles() { return circles; }

    public static void spawnParticle(Location location, ParticlePlace place, Colors color) {
        new ParticleBuilder(ParticleEffect.REDSTONE, location)
                .setColor(color.getBukkitColor())
                .display();
    }
}
