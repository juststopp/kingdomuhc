package fr.juststop.dev.kingdomuhc.items.qin.rishin;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
import fr.juststop.dev.kingdomuhc.roles.qin.RiShin;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.enums.Colors;
import fr.juststop.dev.kingdomuhc.utils.enums.ParticlePlace;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemClickHandler;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemInteractHandler;
import fr.juststop.dev.kingdomuhc.utils.items.GameItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CommandantDe1000Hommes extends GameItem {

    private boolean isInUse = false;
    private int scheduler;

    public CommandantDe1000Hommes(RiShin role) {
        super(
                Language.ITEM_SHIN_1000_HOMMES_NAME.getMessage(),
                Language.ITEM_SHIN_1000_HOMMES_DESC.getAsLore(),
                20,
                new ItemStack(Material.NETHER_STAR),
                0,
                role
        );
    }

    @Override
    public void handleClick(CustomItemClickHandler handler) {
    }

    @Override
    public void handleInteract(CustomItemInteractHandler handler) {
        super.handleInteract(handler);

        if (handler.isCancelled()) return;


        UhcPlayer player = KingdomUHC.getInstance().getGameManager().getPlayers().get(handler.getEvent().getPlayer());
        final int[] speed = {player.getSpeedPercentage()};
        final int[] strength = {player.getStrengthPercentage()};
        if (isInUse) {
            endPower(player, speed, strength);

            Bukkit.getScheduler().cancelTask(scheduler);
            return;
        }

        player.setSpeedPercentage(speed[0] + 20);
        player.setStrengthPercentage(strength[0] + 10);
        player.getParticles().put(ParticlePlace.FOOT, Colors.GREEN);
        player.getActionBar().add(this.getName());

        isInUse = true;

        new MessageBuilder(Language.PREFIX.getMessage())
                .addText(Language.ITEM_ACTIVATED.getMessage().replace("%item_name%", this.getName()))
                .sendMessage(player.getPlayer());

        scheduler = Bukkit.getScheduler().runTaskLater(KingdomUHC.getInstance(), () -> {
            endPower(player, speed, strength);
        }, 20L * 90).getTaskId();

    }

    private void endPower(UhcPlayer player, int[] speed, int[] strength) {
        speed[0] = player.getSpeedPercentage();
        strength[0] = player.getStrengthPercentage();

        player.setSpeedPercentage(speed[0] - 20);
        player.setStrengthPercentage(strength[0] - 10);
        player.getParticles().remove(ParticlePlace.FOOT);
        player.getActionBar().remove(this.getName());

        isInUse = false;

        new MessageBuilder(Language.PREFIX.getMessage())
                .addText(Language.ITEM_FINISHED.getMessage().replace("%item_name%", this.getName()))
                .sendMessage(player.getPlayer());
    }
}
