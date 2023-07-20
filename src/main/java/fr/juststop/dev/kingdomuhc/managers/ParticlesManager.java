package fr.juststop.dev.kingdomuhc.managers;

import fr.juststop.dev.kingdomuhc.utils.enums.Colors;
import fr.juststop.dev.kingdomuhc.utils.enums.ParticlePlace;
import org.bukkit.Location;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

public class ParticlesManager {

    public static void spawnParticle(Location location, ParticlePlace place, Colors color) {
        new ParticleBuilder(ParticleEffect.REDSTONE, location)
                .setColor(color.getBukkitColor())
                .display();
    }
}
