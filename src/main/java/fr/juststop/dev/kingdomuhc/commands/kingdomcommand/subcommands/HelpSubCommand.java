package fr.juststop.dev.kingdomuhc.commands.kingdomcommand.subcommands;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import org.bukkit.entity.Player;

import java.util.Map;

public class HelpSubCommand extends Command {

    public HelpSubCommand(String name) { super(name, Language.CMD_KINGDOM_SUB_HELP_DESC.getMessage(), ""); }

    @Override
    public void run(Player player, String[] args) {
        new MessageBuilder(Language.PREFIX.getMessage())
                .addText(Language.CMD_KINGDOM_SUB_HELP_MESSAGE.getMessage())
                .sendMessage(player);
        for(Map.Entry<String, Command> set : KingdomUHC.getInstance().getCommands().get("kingdom").getSubcommands().entrySet()) {
            if(player.hasPermission(set.getValue().getPermission())) player.sendMessage(Language.FORMAT_LIST_KEY_VALUE.getMessage().replace("%key%", set.getKey()).replace("%value%", set.getValue().getDescription()));
        }
    }
}
