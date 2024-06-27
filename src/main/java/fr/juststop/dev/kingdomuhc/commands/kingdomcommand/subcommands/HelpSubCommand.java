package fr.juststop.dev.kingdomuhc.commands.kingdomcommand.subcommands;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.Language;
import org.bukkit.entity.Player;

import java.util.Map;

public class HelpSubCommand extends Command {

    public HelpSubCommand(String name) { super(name, new Language("commands.kingdom.subcommands.help.description").getMessage(), ""); }

    @Override
    public void run(Player player, String[] args) {
        new MessageBuilder(new Language("prefix").getMessage())
                .addText(new Language("commands.kingdom.subcommands.help.message").getMessage())
                .sendMessage(player);
        for(Map.Entry<String, Command> set : KingdomUHC.getInstance().getCommands().get("kingdom").getSubcommands().entrySet()) {
            if(player.hasPermission(set.getValue().getPermission())) player.sendMessage(new Language("format.list_key_value").getMessage().replace("%key%", set.getKey()).replace("%value%", set.getValue().getDescription()));
        }
    }
}
