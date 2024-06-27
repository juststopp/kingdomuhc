package fr.juststop.dev.kingdomuhc.commands.worldcommand.subcommands;

import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.managers.world.ChunkLoader;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.Language;
import org.bukkit.entity.Player;

public class LoadSubCommand extends Command {

    public LoadSubCommand(String name) { super(name, new Language("commands.world.subcommands.load.description").getMessage(), "blastmc.uhc.world.load"); }

    @Override
    public void run(Player player, String[] args) {

        new MessageBuilder(new Language("prefix").getMessage())
                .addText(new Language("commands.world.subcommands.load.starting").getMessage())
                .sendMessage(player);

        new ChunkLoader(63);
    }
}
