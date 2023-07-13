package fr.juststop.dev.kingdomuhc.commands.languagecommand;

import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import org.apache.logging.log4j.message.Message;
import org.bukkit.entity.Player;

public class LanguageCommand extends Command {

    public LanguageCommand(String name) { super(name, Language.CMD_LANGUAGE_DESC.getMessage(), "blastmc.uhc.reload_language"); }

    @Override
    public void run(Player player, String[] args) {
        new MessageBuilder(Language.ERROR_PREFIX.getMessage())
                .addText(Language.CMD_NOPERM.getMessage())
                .sendMessage(player);
    }

}
