package fr.juststop.dev.kingdomuhc.items.qin.rishin;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
import fr.juststop.dev.kingdomuhc.roles.qin.RiShin;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemClickHandler;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemInteractHandler;
import fr.juststop.dev.kingdomuhc.utils.items.GameItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.atomic.AtomicInteger;

public class CommandantDe100Hommes extends GameItem {

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
        player.setSpeedPercentage(speed[0] + 10);

        new MessageBuilder(Language.PREFIX.getMessage())
                .addText(Language.ITEM_ACTIVATED.getMessage().replace("%item_name%", this.getName()))
                .sendMessage(player.getPlayer());

        Bukkit.getScheduler().runTaskLater(KingdomUHC.getInstance(), () -> {
            speed[0] = player.getSpeedPercentage();
            player.setSpeedPercentage(speed[0] - 10);

            new MessageBuilder(Language.PREFIX.getMessage())
                    .addText(Language.ITEM_FINISHED.getMessage().replace("%item_name%", this.getName()))
                    .sendMessage(player.getPlayer());
        }, 20L * 20);

    }

}
