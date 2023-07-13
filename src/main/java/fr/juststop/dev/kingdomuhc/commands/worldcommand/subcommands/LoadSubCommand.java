package fr.juststop.dev.kingdomuhc.commands.worldcommand.subcommands;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.managers.world.ChunkLoader;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import org.bukkit.entity.Player;

public class LoadSubCommand extends Command {

    public LoadSubCommand(String name) { super(name, Language.CMD_WORLD_SUB_LOAD_DESC.getMessage(), "blastmc.uhc.world.load"); }

    @Override
    public void run(Player player, String[] args) {

        new MessageBuilder(Language.PREFIX.getMessage())
                .addText(Language.CMD_WORLD_SUB_LOAD_STARTING.getMessage())
                .sendMessage(player);

        new ChunkLoader(1000);
    }
}
