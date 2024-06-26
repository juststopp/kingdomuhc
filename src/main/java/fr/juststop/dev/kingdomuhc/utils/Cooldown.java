package fr.juststop.dev.kingdomuhc.utils;

import java.time.Instant;

public class Cooldown {

    private Instant start;
    public final int cooldown;

    public Cooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void start() {
        start = Instant.now();
    }

    public boolean isActive() {
        if(start == null) return false;
        return Instant.now().getEpochSecond() - start.getEpochSecond() < this.cooldown;
    }

    public long getRemaining() {
        return Math.max(0, cooldown - (Instant.now().getEpochSecond() - start.getEpochSecond()));
    }

}
