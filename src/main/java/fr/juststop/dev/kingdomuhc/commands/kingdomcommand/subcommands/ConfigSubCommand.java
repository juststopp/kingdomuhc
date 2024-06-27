package fr.juststop.dev.kingdomuhc.commands.kingdomcommand.subcommands;

import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.Language;
import org.bukkit.entity.Player;

public class ConfigSubCommand extends Command {


    public ConfigSubCommand(String name) { super(name, new Language("commands.kingdom.subcommands.config.description").getMessage(), ""); }

    @Override
    public void run(Player player, String[] args) {
        new MessageBuilder(new Language("error_prefix").getMessage())
                .addText(new Language("commands.not_implemented").getMessage())
                .sendMessage(player);
    }
}
