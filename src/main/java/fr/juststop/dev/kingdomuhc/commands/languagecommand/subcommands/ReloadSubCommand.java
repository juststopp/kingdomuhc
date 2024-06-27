package fr.juststop.dev.kingdomuhc.commands.languagecommand.subcommands;

import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.Language;
import org.bukkit.entity.Player;

public class ReloadSubCommand extends Command {

    public ReloadSubCommand(String name) { super(name, new Language("commands.language.subcommands.reload.description").getMessage(), "blastmc.uhc.reload_language"); }

    @Override
    public void run(Player player, String[] args) {
        Language.init();
        new MessageBuilder(new Language("prefix").getMessage())
                .addText(new Language("commands.language.subcommands.reload.message").getMessage())
                .sendMessage(player);
    }

}
