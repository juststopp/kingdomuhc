package fr.juststop.dev.kingdomuhc.managers.particles;

import org.bukkit.Location;
import xyz.xenondevs.particle.ParticleEffect;

public class ParticleCircle {

    private ParticleEffect effect;
    private int radius;
    private Location location;
    
    public ParticleCircle(ParticleEffect effect, int radius, Location location) {
        this.effect = effect;
        this.radius = radius;
        this.location = location;
    }

    public ParticleEffect getEffect() { return effect; }
    public int getRadius() { return radius; }
    public Location getLocation() { return location; }
}
