package fr.juststop.dev.kingdomuhc.commands.languagecommand;

import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.Language;
import org.bukkit.entity.Player;

public class LanguageCommand extends Command {

    public LanguageCommand(String name) { super(name, new Language("commands.language.description").getMessage(), "blastmc.uhc.reload_language"); }

    @Override
    public void run(Player player, String[] args) {
        new MessageBuilder(new Language("error_prefix").getMessage())
                .addText(new Language("commands.not_implemented").getMessage())
                .sendMessage(player);
    }

}
