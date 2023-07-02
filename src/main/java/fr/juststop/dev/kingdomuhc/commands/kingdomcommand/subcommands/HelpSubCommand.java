package fr.juststop.dev.kingdomuhc.commands.kingdomcommand.subcommands;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

public class HelpSubCommand extends Command {

    public HelpSubCommand(String name) { super(name, "&7Liste les commandes disponibles.", ""); }

    @Override
    public void run(Player player, String[] args) {
        new MessageBuilder(KingdomUHC.getInstance().getPrefix())
                .addText("&7Voici la liste des sous-commandes que vous pouvez &7utiliser:")
                .sendMessage(player);
        for(Map.Entry<String, Command> set : KingdomUHC.getInstance().getCommands().get("kingdom").getSubcommands().entrySet()) {
            if(player.hasPermission(set.getValue().getPermission())) player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8- &6" + set.getKey() + "&7: " + set.getValue().getDescription()));
        }
    }
}
