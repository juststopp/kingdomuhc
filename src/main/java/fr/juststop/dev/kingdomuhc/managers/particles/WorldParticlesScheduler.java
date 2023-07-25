package fr.juststop.dev.kingdomuhc.managers.particles;

import fr.juststop.dev.kingdomuhc.utils.Utils;
import org.bukkit.scheduler.BukkitRunnable;

public class WorldParticlesScheduler extends BukkitRunnable {

    @Override
    public void run() {

        for(ParticleCircle circle : ParticlesManager.getCircles()) { Utils.summonParticleCircle(circle); }

    }

}
