package fr.juststop.dev.kingdomuhc.commands.languagecommand.subcommands;

import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import org.bukkit.entity.Player;

public class ReloadSubCommand extends Command {

    public ReloadSubCommand(String name) { super(name, Language.CMD_LANGUAGE_SUB_RELOAD_DESC.getMessage(), "blastmc.uhc.reload_language"); }

    @Override
    public void run(Player player, String[] args) {
        Language.init();
        new MessageBuilder(Language.PREFIX.getMessage())
                .addText(Language.CMD_LANGUAGE_SUB_RELOAD_RELOADED.getMessage())
                .sendMessage(player);
    }

}
