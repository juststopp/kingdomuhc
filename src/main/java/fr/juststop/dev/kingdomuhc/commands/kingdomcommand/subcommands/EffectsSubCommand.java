package fr.juststop.dev.kingdomuhc.commands.kingdomcommand.subcommands;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
import fr.juststop.dev.kingdomuhc.utils.Language;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EffectsSubCommand extends Command {

    public EffectsSubCommand(String name) {
        super(name, Language.CMD_KINGDOM_SUB_EFFECTS_DESC.getMessage(), "");
    }

    @Override
    public void run(Player player, String[] args) {

        String[] messages = Language.CMD_KINGDOM_SUB_EFFECTS_MESSAGE.getAsLore();
        UhcPlayer uhcPlayer = KingdomUHC.getInstance().getGameManager().getPlayers().get(player);

        int speed = 0;
        int strength = 0;
        int resistance = 0;

        for(PotionEffect effect : player.getActivePotionEffects()) {
            if(effect.getType().equals(PotionEffectType.SPEED)) speed = (effect.getAmplifier() + 1) * 20;
            if(effect.getType().equals(PotionEffectType.INCREASE_DAMAGE)) strength = (effect.getAmplifier() + 1) * 20;
            if(effect.getType().equals(PotionEffectType.DAMAGE_RESISTANCE)) resistance = (effect.getAmplifier() + 1) * 20;
        }

        for(String message : messages) {
            player.sendMessage(message
                    .replace("%speed%", String.valueOf(uhcPlayer.getSpeedPercentage() + speed))
                    .replace("%strength%", String.valueOf(uhcPlayer.getStrengthPercentage() + strength))
                    .replace("%resistance%", String.valueOf(uhcPlayer.getResistancePercentage() + resistance))
            );
        }

    }

}
