package fr.juststop.dev.kingdomuhc.managers;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.managers.game.GameManager;
import fr.juststop.dev.kingdomuhc.roles.Role;
import fr.juststop.dev.kingdomuhc.utils.enums.RolesStatus;
import org.bukkit.entity.Player;

import java.util.*;

public class RolesManager {

    public static void distributeRoles() {
        GameManager manager = KingdomUHC.getInstance().getGameManager();
        HashMap<Player, UhcPlayer> players = manager.getPlayers();
        List<Role> roles = manager.getRoles();
        List<Role> roles_list = new ArrayList<>(roles);

        if(!roles.isEmpty()) {
            for(UhcPlayer player : players.values()) {
                Role role = roles_list.get((int) Math.floor(Math.random() * roles_list.toArray().length));

                player.setRole(role);

                roles_list.remove(role);
            }
        }

        manager.getConfig().ROLES_STATUS = RolesStatus.GIVEN;
    }

}
