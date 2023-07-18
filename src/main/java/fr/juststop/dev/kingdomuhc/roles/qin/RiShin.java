package fr.juststop.dev.kingdomuhc.roles.qin;

import fr.juststop.dev.kingdomuhc.roles.Role;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.enums.Camps;

public class RiShin extends Role {

    public RiShin() {
        super(
                "Ri Shin",
                Camps.QIN,
                Language.ROLES_QIN_SHIN_SHORT_DESC.getAsLore(),
                Language.ROLES_QIN_SHIN_LONG_DESC.getMessage());
    }

}
