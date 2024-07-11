package fr.juststop.dev.kingdomuhc.roles.zhao;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.items.zhao.bananji.Force;
import fr.juststop.dev.kingdomuhc.roles.Role;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.Utils;
import fr.juststop.dev.kingdomuhc.utils.enums.Camps;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BaNanJi extends Role {

    public BaNanJi() {
        super(
                new Language("roles.zhao.bananji.name").getMessage(),
                Camps.ZHAO,
                new Language("roles.zhao.bananji.long_desc").getAsList()
        );
    }

    @Override
    public void init(boolean ignoreEffects) {
        super.init(ignoreEffects);
        this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 99999, 0, true, false));

        Force force = new Force(this);
        Utils.addItemIfPlayerNotHas(this.getPlayer(), force.getItemStack());

        for(Role role : KingdomUHC.getInstance().getGameManager().getRoles()) {
            if(role.getName().equalsIgnoreCase(new Language("roles.zhao.riboku.name").getMessage())) {
                new MessageBuilder(new Language("prefix").getMessage())
                        .addText(role.getCamp().getColor().getChatColor() + role.getName() + " &7est: &c" + role.getPlayer().getName())
                        .sendMessage(this.getPlayer());
            }
        }
    }

}
