package fr.juststop.dev.kingdomuhc.managers.areas;


import org.bukkit.Location;

public class Area {

    private Location location;
    private AreaOptions options;

    public Area(Location location, AreaOptions options) {
        this.location = location;
        this.options = options;
    }

    public Location getLocation() { return location;}
    public AreaOptions getOptions() { return options; }
}
