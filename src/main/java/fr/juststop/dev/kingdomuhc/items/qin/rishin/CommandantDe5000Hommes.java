package fr.juststop.dev.kingdomuhc.items.qin.rishin;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
import fr.juststop.dev.kingdomuhc.managers.areas.Area;
import fr.juststop.dev.kingdomuhc.managers.areas.AreaOptions;
import fr.juststop.dev.kingdomuhc.managers.particles.ParticleCircle;
import fr.juststop.dev.kingdomuhc.managers.particles.ParticlesManager;
import fr.juststop.dev.kingdomuhc.roles.qin.RiShin;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.Utils;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemInteractHandler;
import fr.juststop.dev.kingdomuhc.utils.items.GameItem;
import fr.juststop.dev.kingdomuhc.utils.Language;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import xyz.xenondevs.particle.ParticleEffect;

import java.sql.Timestamp;
import java.time.Instant;

public class CommandantDe5000Hommes extends GameItem {

    private RiShin role;
    private ParticleCircle circle;
    private Area area;
    private Timestamp lastTp;

    private int scheduler;
    private boolean zoneInUse = false;

    public CommandantDe5000Hommes(RiShin role) {
        super(
                Language.ITEM_SHIN_5000_HOMMES_NAME.getMessage(),
                Language.ITEM_SHIN_5000_HOMMES_DESC.getAsLore(),
                25,
                new ItemStack(Material.NETHER_STAR),
                0,
                role
        );

        this.role = role;
    }

    @Override
    public void handleInteract(CustomItemInteractHandler handler) {
        super.handleInteract(handler);

        if(handler.isCancelled()) return;

        UhcPlayer player = KingdomUHC.getInstance().getGameManager().getPlayers().get(handler.getEvent().getPlayer());

        if(handler.getAction() == Action.RIGHT_CLICK_AIR || handler.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if (zoneInUse) {
                endPower(player);

                Bukkit.getScheduler().cancelTask(scheduler);
                return;
            }

            ParticleCircle circle = new ParticleCircle(ParticleEffect.REDSTONE, 25, player.getPlayer().getLocation());
            ParticlesManager.getCircles().add(circle);

            zoneInUse = true;

            this.circle = circle;
            this.area = new Area(player.getPlayer().getLocation(),
                    new AreaOptions(player, 25, false, 10)
            );

            player.getActionBar().add(this.getName());
            player.getAreas().add(area);

            new MessageBuilder(Language.PREFIX.getMessage())
                    .addText(Language.ITEM_ACTIVATED.getMessage().replace("%item_name%", this.getName() + " - Zone"))
                    .sendMessage(player.getPlayer());

            scheduler = Bukkit.getScheduler().runTaskLater(KingdomUHC.getInstance(), () -> {
                endPower(player);
            }, 20L * 20).getTaskId();

        } else if(handler.getAction() == Action.LEFT_CLICK_AIR || handler.getAction() == Action.LEFT_CLICK_BLOCK) {

            if(!zoneInUse
                    || this.area.getLocation().distance(player.getPlayer().getLocation()) > area.getOptions().radius
            ) {
                new MessageBuilder(Language.ERROR_PREFIX.getMessage())
                        .addText(Language.ITEM_NOT_IN_AREA.getMessage())
                        .sendMessage(player.getPlayer());

                 return;
            }

            if(this.lastTp.compareTo(Timestamp.from(Instant.now())) < 1000 * 30) {
                new MessageBuilder(Language.ERROR_PREFIX.getMessage())
                        .addText(Language.ITEM_COOLDOWN.getMessage())
                        .sendMessage(player.getPlayer());

                return;
            }

            Player target = Utils.isTargetingAPlayer(player.getPlayer(), 50);
            if(player != null) {

                player.getPlayer().teleport(target);
                new MessageBuilder(Language.PREFIX.getMessage())
                        .addText(Language.ITEM_ACTIVATED.getMessage().replace("%item_name%", this.getName() + " - Teleport"))
                        .sendMessage(player.getPlayer());

            } else {

                new MessageBuilder(Language.ERROR_PREFIX.getMessage())
                        .addText(Language.ITEM_NO_TARGET.getMessage())
                        .sendMessage(player.getPlayer());

            }

        }

    }

    public void endPower(UhcPlayer player) {
        Bukkit.getScheduler().cancelTask(scheduler);
        ParticlesManager.getCircles().remove(circle);

        player.getAreas().remove(area);
        player.getActionBar().remove(this.getName());

        zoneInUse = false;

        new MessageBuilder(Language.PREFIX.getMessage())
                .addText(Language.ITEM_FINISHED.getMessage().replace("%item_name%", this.getName()))
                .sendMessage(player.getPlayer());
    }

}
