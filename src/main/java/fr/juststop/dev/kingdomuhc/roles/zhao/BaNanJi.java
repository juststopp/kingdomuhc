package fr.juststop.dev.kingdomuhc.roles.zhao;

import fr.juststop.dev.kingdomuhc.roles.Role;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.enums.Camps;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BaNanJi extends Role {

    public BaNanJi() {
        super(
                Language.ROLES_ZHAO_BANANJI_NAME.getMessage(),
                Camps.ZHAO,
                Language.ROLES_ZHAO_BANANJI_SHORT_DESC.getAsLore(),
                Language.ROLES_ZHAO_BANANJI_LONG_DESC.getMessage()
        );
    }

    @Override
    public void init() {
        super.init();
        this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 99999, 0, true, false));
    }

}
