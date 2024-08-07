package fr.juststop.dev.kingdomuhc.commands.worldcommand.subcommands.createsubcommand.subcommands;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.Language;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.minecraft.server.v1_8_R3.GameRules;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import java.util.Objects;

public class ConfirmSubCommand extends Command {

    public ConfirmSubCommand(String name) { super(name, new Language("commands.world.subcommands.create.subcommands.confirm.description").getMessage(), "blastmc.uhc.world.create"); }

    @Override
    public void run(Player player, String[] args) {
        new MessageBuilder(new Language("prefix").getMessage())
                .addText(new Language("commands.world.subcommands.create.subcommands.confirm.creating").getMessage())
                .sendMessage(player);

        if(!Objects.equals(player.getWorld().getName(), "world")) player.teleport(new Location(Bukkit.getWorld("world"), 0, 202, 0));

        if(Bukkit.getWorld("game") != null) KingdomUHC.getInstance().getWorldManager().deleteWorld(Bukkit.getWorld("game"));

        World world = Bukkit.createWorld(
                new WorldCreator("game")
                        .generatorSettings("{\"coordinateScale\":684.412,\"heightScale\":684.412,\"lowerLimitScale\":512.0,\"upperLimitScale\":512.0,\"depthNoiseScaleX\":200.0,\"depthNoiseScaleZ\":200.0,\"depthNoiseScaleExponent\":0.8546479,\"mainNoiseScaleX\":80.0,\"mainNoiseScaleY\":160.0,\"mainNoiseScaleZ\":80.0,\"baseSize\":8.5,\"stretchY\":12.0,\"biomeDepthWeight\":1.0,\"biomeDepthOffset\":0.0,\"biomeScaleWeight\":1.0,\"biomeScaleOffset\":0.0,\"seaLevel\":63,\"useCaves\":true,\"useDungeons\":true,\"dungeonChance\":8,\"useStrongholds\":true,\"useVillages\":true,\"useMineShafts\":true,\"useTemples\":true,\"useMonuments\":true,\"useRavines\":true,\"useWaterLakes\":true,\"waterLakeChance\":4,\"useLavaLakes\":true,\"lavaLakeChance\":80,\"useLavaOceans\":false,\"fixedBiome\":-1,\"biomeSize\":4,\"riverSize\":4,\"dirtSize\":33,\"dirtCount\":10,\"dirtMinHeight\":0,\"dirtMaxHeight\":256,\"gravelSize\":33,\"gravelCount\":8,\"gravelMinHeight\":0,\"gravelMaxHeight\":256,\"graniteSize\":38,\"graniteCount\":0,\"graniteMinHeight\":0,\"graniteMaxHeight\":80,\"dioriteSize\":33,\"dioriteCount\":0,\"dioriteMinHeight\":0,\"dioriteMaxHeight\":80,\"andesiteSize\":33,\"andesiteCount\":0,\"andesiteMinHeight\":0,\"andesiteMaxHeight\":80,\"coalSize\":17,\"coalCount\":20,\"coalMinHeight\":0,\"coalMaxHeight\":128,\"ironSize\":9,\"ironCount\":20,\"ironMinHeight\":0,\"ironMaxHeight\":64,\"goldSize\":9,\"goldCount\":4,\"goldMinHeight\":0,\"goldMaxHeight\":32,\"redstoneSize\":8,\"redstoneCount\":8,\"redstoneMinHeight\":0,\"redstoneMaxHeight\":16,\"diamondSize\":8,\"diamondCount\":2,\"diamondMinHeight\":0,\"diamondMaxHeight\":16,\"lapisSize\":7,\"lapisCount\":2,\"lapisCenterHeight\":16,\"lapisSpread\":16}")
        );
        world.setGameRuleValue("keepInventory", "true");
        player.teleport(new Location(world, 0, 200, 0));

        new MessageBuilder(new Language("prefix").getMessage())
                .addText(new Language("commands.world.subcommands.create.subcommands.confirm.created").getMessage())
                .sendMessage(player);


        new MessageBuilder("&8- ")
                .addClickAndHoverMessage(
                        new Language("commands.world.subcommands.create.subcommands.confirm.actions.delete_and_create").getMessage(),
                        ClickEvent.Action.RUN_COMMAND,
                        HoverEvent.Action.SHOW_TEXT,
                        "/world create confirm",
                        new Language("commands.world.subcommands.create.subcommands.confirm.actions.delete_and_create").getMessage()
                )
                .sendMessage(player);

        new MessageBuilder("&8- ")
                .addClickAndHoverMessage(
                        new Language("commands.world.subcommands.create.subcommands.confirm.actions.save_and_load").getMessage(),
                        ClickEvent.Action.RUN_COMMAND,
                        HoverEvent.Action.SHOW_TEXT,
                        "/world load",
                        new Language("commands.world.subcommands.create.subcommands.confirm.actions.save_and_load").getMessage()
                )
                .sendMessage(player);
    }

}
