package fr.juststop.dev.kingdomuhc.commands.kingdomcommand.subcommands;

import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import org.bukkit.entity.Player;

public class ConfigSubCommand extends Command {


    public ConfigSubCommand(String name) { super(name, Language.CMD_KINGDOM_SUB_CONFIG_DESC.getMessage(), ""); }

    @Override
    public void run(Player player, String[] args) {
        new MessageBuilder(Language.ERROR_PREFIX.getMessage())
                .addText(Language.CMD_NOT_IMPLEMENTED.getMessage())
                .sendMessage(player);
    }
}
