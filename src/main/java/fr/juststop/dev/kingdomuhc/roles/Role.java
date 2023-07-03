package fr.juststop.dev.kingdomuhc.roles;

import fr.juststop.dev.kingdomuhc.utils.enums.Camps;

public class Role {

    private final String name;
    private final Camps camp;

    public Role(String name, Camps camp) {

        this.name = name;
        this.camp = camp;

    }

    public String getName() { return camp.getColor().getChatColor() + name; }
    public Camps getCamp() { return camp; }
}
