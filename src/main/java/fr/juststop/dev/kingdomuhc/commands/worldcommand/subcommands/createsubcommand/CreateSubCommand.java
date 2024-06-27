package fr.juststop.dev.kingdomuhc.commands.worldcommand.subcommands.createsubcommand;

import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.Language;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.entity.Player;

public class CreateSubCommand extends Command {

    public CreateSubCommand(String name) { super(name, new Language("commands.world.subcommands.create.description").getMessage(), "blastmc.uhc.world.create"); }

    @Override
    public void run(Player player, String[] args) {
        new MessageBuilder(new Language("prefix").getMessage())
                .addText(new Language("commands.world.subcommands.create.sure").getMessage())
                .addClickAndHoverMessage(new Language("commands.world.subcommands.create.yes").getMessage(), ClickEvent.Action.RUN_COMMAND, HoverEvent.Action.SHOW_TEXT, "/world create confirm", new Language("commands.world.subcommands.create.yes").getMessage())
                .sendMessage(player);
    }
}
