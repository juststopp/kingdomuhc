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

public class CommandantDe100Hommes extends GameItem {

    private boolean isInUse = false;
    private int scheduler;

    public CommandantDe100Hommes(RiShin role) {
        super(
                Language.ITEM_SHIN_100_HOMMES_NAME.getMessage(),
                Language.ITEM_SHIN_100_HOMMES_DESC.getAsLore(),
                10,
                new ItemStack(Material.NETHER_STAR),
                0,
                role
        );
    }

    @Override
    public void handleClick(CustomItemClickHandler handler) {}

    @Override
    public void handleInteract(CustomItemInteractHandler handler) {
        super.handleInteract(handler);

        if(handler.isCancelled()) return;

        UhcPlayer player = KingdomUHC.getInstance().getGameManager().getPlayers().get(handler.getEvent().getPlayer());
        final int[] speed = {player.getSpeedPercentage()};
        if(isInUse) {
            endPower(player, speed);

            Bukkit.getScheduler().cancelTask(scheduler);
            return;
        }

        player.setSpeedPercentage(speed[0] + 10);
        player.getParticles().put(ParticlePlace.FOOT, Colors.YELLOW);

        isInUse = true;

        new MessageBuilder(Language.PREFIX.getMessage())
                .addText(Language.ITEM_ACTIVATED.getMessage().replace("%item_name%", this.getName()))
                .sendMessage(player.getPlayer());

        scheduler = Bukkit.getScheduler().runTaskLater(KingdomUHC.getInstance(), () -> {
            endPower(player, speed);
        }, 20L * 20).getTaskId();

    }

    private void endPower(UhcPlayer player, int[] speed) {
        speed[0] = player.getSpeedPercentage();

        player.setSpeedPercentage(speed[0] - 10);
        player.getParticles().remove(ParticlePlace.FOOT);
        player.getActionBar().add(this.getName());

        isInUse = false;

        Bukkit.getScheduler().cancelTask(scheduler);

        new MessageBuilder(Language.PREFIX.getMessage())
                .addText(Language.ITEM_FINISHED.getMessage().replace("%item_name%", this.getName()))
                .sendMessage(player.getPlayer());
    }

}
