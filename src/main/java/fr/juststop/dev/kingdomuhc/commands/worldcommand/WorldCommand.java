package fr.juststop.dev.kingdomuhc.commands.worldcommand;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

public class WorldCommand extends Command {

    public WorldCommand(String name) { super(name, "&7Permet de gérer la génération du monde.", "blastmc.uhc.world"); }

    @Override
    public void run(Player player, String[] args) {
        new MessageBuilder(KingdomUHC.getInstance().getPrefix())
                .addText("&7Voici la liste des sous-commandes que vous pouvez &7utiliser:")
                .sendMessage(player);
        for(Map.Entry<String, Command> set : KingdomUHC.getInstance().getCommands().get("world").getSubcommands().entrySet()) {
            if(player.hasPermission(set.getValue().getPermission())) player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8- &6" + set.getKey() + "&7: " + set.getValue().getDescription()));
        }
    }
}
